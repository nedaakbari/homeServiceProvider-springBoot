package ir.maktab.homeserviceprovider.web;

import ir.maktab.configuration.LastViewInterceptor;
import ir.maktab.data.enums.OfferStatus;
import ir.maktab.data.enums.OrderState;
import ir.maktab.dto.*;
import ir.maktab.service.exception.DuplicateData;
import ir.maktab.service.exception.LessAmount;
import ir.maktab.service.interfaces.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.*;

@Controller
@AllArgsConstructor
//@SessionAttributes(value = {"expert", "expertDto", "subList"})
public class ExpertController {

    private final ExpertService service;
    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;
    private final OrderService orderService;
    private final OfferService offerService;


    @GetMapping(value = "/expert/dashboard")
    public String dashboard(Principal principal, Model model, HttpSession httpSession, HttpServletRequest request) {
        ExpertDto expertLogin = service.findByEmail(principal.getName());
        request.getSession().setAttribute("email", expertLogin.getEmail());
        //ImageFile imageFile = expertLogin.getProfileImage().get(1);
        httpSession.setAttribute("expert", expertLogin);
        Map<String, Object> map = new HashMap<>();
        map.put("name", expertLogin.getFirstName());
        map.put("lastName", expertLogin.getLastName());
        map.put("role", expertLogin.getRole());
        map.put("creditCart", expertLogin.getCreditCart());
        map.put("userName", expertLogin.getUsername());
        map.put("password", expertLogin.getPassword());
        map.put("accNum", expertLogin.getAccNumber());
        //  map.put("image",imageFile);
        model.addAttribute("expert", expertLogin);
        model.addAllAttributes(map);
        return "expertPanel/profile";
    }

    @GetMapping(value = "/expert/registerAccNumber")
    public String registerAccNumber() {
        return "expertPanel/editAccNumber";
    }

    @PostMapping(value = "/expert/registerAccNumber")
    public String editCreditCart(@SessionAttribute("expertDto") ExpertDto expertDto,
                                 @RequestParam("accNumber") String accNumber,
                                 Model model, HttpServletRequest request) {
        long accNumberLong = Long.parseLong(accNumber);
        service.updateAccNumber(accNumberLong, expertDto);
        expertDto.setAccNumber(accNumberLong);
        model.addAttribute("accNumber", accNumber);
        request.getSession().setAttribute("expertDto", expertDto);
        return "redirect:/expert/dashboard";
    }

    @GetMapping(value = "/expert/editPass")
    public String editPass() {
        return "expertPanel/editPass";
    }

    @PostMapping(value = "/expert/editPass")
    public String editPassword(/*@ModelAttribute("login") ExpertDto expertDto,*/
            HttpServletRequest request
            , @RequestParam("newPass") String newPassword
            , @RequestParam("oldPass") String oldPassword) {
        ExpertDto expert = (ExpertDto) request.getSession().getAttribute("expertDto");
        service.updatePassword(newPassword, oldPassword, expert);
        expert.setPassword(newPassword);
        request.getSession().setAttribute("expertDto", expert);
        return "redirect:/expert/dashboard";//todo how to save this customer with new password
    }


    @GetMapping(value = "/expert/categoryList")
    public String showCategoryList(Model model) {
        List<CategoryDto> list = categoryService.getAll();
        model.addAttribute("list", list);
        return "expertPanel/categoryList";
    }

    @GetMapping("/expert/showSubCategory/{title}")
    public String showAllSubCategory(@PathVariable String title, Model model) {
        List<SubCategoryDto> list = subCategoryService.findAllSubCategoryOfACategory(title);
        model.addAttribute("list", list);
        return "expertPanel/viewSubCategory";
    }

    @GetMapping(value = "/expert/addingSub/{title}")
    public String addingSub(@ModelAttribute("expertDto") ExpertDto dto,
                            @PathVariable String title,
                            HttpServletRequest request,
                            Model model) {
        ExpertDto expert = (ExpertDto) request.getSession().getAttribute("expertDto");
        service.addSubCategoryToExpertList(expert, title);
        model.addAttribute("title", title);
        model.addAttribute("expertDto", expert);
        //return "expertPages/speciality";
        return "redirect:/expert/showSpeciality";
    }

    @GetMapping(value = "/expert/delete/{title}")
    public String deleteSub(@PathVariable String title,
                            HttpServletRequest request, Model model) {
        ExpertDto expert = (ExpertDto) request.getSession().getAttribute("expertDto");
        service.removeSubCategoryFromExpertList(expert, title);
        Set<SubCategoryDto> subCategoryOfAnExpert = subCategoryService.findSubCategoryOfAnExpert(expert);
        model.addAttribute("expertDto", expert);
        return "redirect:/expert/showSpeciality";
    }

    @GetMapping(value = "/expert/showSpeciality")
    public String showSpeciality(@ModelAttribute("expertDto") ExpertDto dto,
                                 HttpServletRequest request, Model model) {
        ExpertDto expert = service.findByEmail(dto.getEmail());
        request.getSession().setAttribute("expertDto", expert);
        Set<SubCategoryDto> subCategoryOfAnExpert = subCategoryService.findSubCategoryOfAnExpert(expert);
        List<SubCategoryDto> subCategory = new ArrayList<>(subCategoryOfAnExpert);
        request.getSession().setAttribute("subList", subCategory);
        model.addAttribute("subList", subCategory);
        return "expertPanel/speciality";
    }

    @GetMapping(value = "/expert/showAllOrders")
    public String showAllOrders(HttpServletRequest request, Model model) {
        ExpertDto expertDto = (ExpertDto) request.getSession().getAttribute("expertDto");
        List<OrdersDto> ordersForExpert = orderService.findOrdersForExpert(expertDto);
        model.addAttribute("ordersForExpert", ordersForExpert);
        return "expertPanel/showAllOrders";//todo dont let offer again or dont show him those orders that offered
    }

    @GetMapping(value = "/expert/offer/{codeNumber}")
    private ModelAndView makeOffer(@PathVariable String codeNumber, Model model,
                                   HttpServletRequest request) {
        ExpertDto expert = (ExpertDto) request.getSession().getAttribute("expertDto");
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(codeNumber);
        if (offerService.isAllowToOffer(expert, codeNumber)) {
        System.out.println(codeNumber);
        modelAndView.addObject("codeNumber", codeNumber);
        request.getSession().setAttribute("codeNumber", codeNumber);
        modelAndView.addObject("offer", new OfferDto());
        modelAndView.setViewName("expertPanel/offerForm");
        } else {
            model.addAttribute("notAllowError", new DuplicateData("you offer for this order already"));
            modelAndView.setViewName("expertPanel/showAllOrders");
        }
        return modelAndView;
    }

    @PostMapping(value = "/expert/placeOffer")
    private String placeOffer(HttpServletRequest request,
                              @ModelAttribute("offer") OfferDto offerDto,
                              Model model) {
        String email = (String) request.getSession().getAttribute("email");
        System.out.println(offerDto.getCodeNumber());
          ExpertDto expertDto = (ExpertDto) request.getSession().getAttribute("expertDto");
        String codeNumber = (String) request.getSession().getAttribute("codeNumber");
        // String codeNumber = (String) model.getAttribute("codeNumber");//todo why null?
        try {
            offerService.saveOffer(offerDto, email, codeNumber);
            ExpertDto expert = service.findByEmail(expertDto.getEmail());
            request.setAttribute("expertDto", expert);
            Map<String, Object> map = new HashMap<>();
            map.put("name", expert.getFirstName());
            map.put("lastName", expert.getLastName());
            map.put("role", expert.getRole());
            map.put("creditCart", expert.getCreditCart());
            map.put("userName", expert.getUsername());
            map.put("password", expert.getPassword());
            model.addAttribute("expert", expert);
            model.addAllAttributes(map);
        } catch (RuntimeException runtimeException) {
            model.addAttribute("error", "field cant be empty");
        }
        return "expertPanel/profile";
    }

    @GetMapping(value = "/expert/listOfOffered")
    public String showListOfOffered(HttpServletRequest request,
                                    Model model) {
        ExpertDto expertDto = (ExpertDto) request.getSession().getAttribute("expertDto");
        try {
            List<OfferDto> allOfferAnExpert = offerService.findAllOfferAnExpert(expertDto);
            model.addAttribute("allOfferAnExpert", allOfferAnExpert);
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "expertPanel/listOfOffered";
    }

    @GetMapping(value = "/expert/orderListOf/{title}")
    public String showAllOrderOfASubCategory(@PathVariable String title,
                                             Model model) {
        try {
            List<OrdersDto> ordersOfSubService = orderService.findOrdersOfSubServices(title);
            model.addAttribute("ordersOfSubService", ordersOfSubService);
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "expertPanel/orderListOfSubcategory";
    }

    @GetMapping(value = "/expert/showAllOffersOfExpert")
    public String showAllOfferOfExpert(HttpServletRequest request, Model model) {
        ExpertDto expertDto = (ExpertDto) request.getSession().getAttribute("expertDto");
        try {
            List<OfferDto> allOfferAnExpert = offerService.findAllOfferAnExpert(expertDto);
            model.addAttribute("allOfferAnExpert", allOfferAnExpert);
        } catch (RuntimeException e) {
            model.addAttribute("e", e.getMessage());
        }
        return "expertPanel/allOfferOfExpert";
    }

    @GetMapping(value = "/expert/showAllOffersOfExpertWithStatus")
    public String showAllOffersOfExpertWithStatus(HttpServletRequest request, Model model) {
        ExpertDto expertDto = (ExpertDto) request.getSession().getAttribute("expertDto");
        try {
            List<OfferDto> acceptedOffer = offerService.findAllOffersAnExpert(expertDto, OfferStatus.ACCEPTED);
            model.addAttribute("acceptedOffer", acceptedOffer);
        } catch (RuntimeException e) {
            model.addAttribute("acceptedError", e.getMessage());
        }
        try {
            List<OfferDto> suspendedOffer = offerService.findAllOffersAnExpert(expertDto, OfferStatus.SUSPENDED);
            model.addAttribute("suspendedOffer", suspendedOffer);
        } catch (RuntimeException e) {
            model.addAttribute("suspendedError", e.getMessage());
        }
        try {
            List<OfferDto> rejectedOffer = offerService.findAllOffersAnExpert(expertDto, OfferStatus.REJECTED);
            model.addAttribute("rejectedOffer", rejectedOffer);
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "expertPanel/acceptedOffer";
    }

    @GetMapping(value = "/expert/startOffer/{codeNumber}")
    public String startOffer(@PathVariable String codeNumber) {
        System.out.println(codeNumber);
        OfferDto offerDto = offerService.findByUUID(codeNumber);
        System.out.println(offerDto);
        System.out.println(offerDto.getCodeNumber());
        OrdersDto orders = offerDto.getOrders();
        orderService.updateState(orders, OrderState.STARTED);
        return "redirect:/expert/showAllOffersOfExpertWithStatus";//todo i dont want two url what can i do
    }

    @GetMapping(value = "/expert/finishOffer/{codeNumber}")
    public String finishOffer(@PathVariable String codeNumber) {

        OfferDto offerDto = offerService.findByUUID(codeNumber);
        OrdersDto orders = offerDto.getOrders();
        orderService.updateState(orders, OrderState.DONE);
        return "redirect:/expert/showAllOffersOfExpertWithStatus";
    }

    @GetMapping(value = "/expert/detailsOfOffer/{codeNumber}")//offer.codeNumber
    public String detailsOfOffer(@PathVariable String codeNumber,
                                 Model model) {
        OfferDto offer = offerService.findByUUID(codeNumber);//todo not came all information of customer
        OrdersDto orders = offer.getOrders();
        Map<String, Object> orderDetailInfo = new HashMap<>();
        orderDetailInfo.put("offerSubmissionDate", offer.getSubmissionDate());
        orderDetailInfo.put("subTitle", orders.getSubCategory().getTitle());
        orderDetailInfo.put("description", orders.getDescription());
        orderDetailInfo.put("doWorkDate", orders.getDoWorkDate());
        orderDetailInfo.put("customerFirstName", orders.getCustomer().getFirstName());
        orderDetailInfo.put("customerLastName", orders.getCustomer().getLastName());
        model.addAllAttributes(orderDetailInfo);
        return "expertPanel/detail-form";
    }

    @ExceptionHandler(value = DuplicateData.class)
    public ModelAndView duplicateEmailExceptionHandler(DuplicateData ex, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        Map<String, Object> model = new HashMap<>();
        model.put("error", ex.getMessage());
        model.put("expert", new ExpertDto());
        return new ModelAndView(lastView, model);//مدلمو از این بایندینگ اکسپنه گرفتم
    }

    @ExceptionHandler(value = LessAmount.class)
    public ModelAndView lessAmountExceptionHandler(LessAmount ex) {
        Map<String, Object> model = new HashMap<>();
        model.put("offer", new OfferDto());
        model.put("error", ex.getMessage());
        return new ModelAndView("expertPanel/offerForm", model);
    }

}
