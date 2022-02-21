package ir.maktab.homeserviceprovider.web;


import ir.maktab.configuration.LastViewInterceptor;
import ir.maktab.data.enums.OrderState;
import ir.maktab.dto.*;
import ir.maktab.service.exception.*;
import ir.maktab.service.interfaces.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@SessionAttributes({"customer", "customerDto", "address", "order", "addressDto", "codeNumber", "orderCodeNumber"})

public class CustomerController {

    private final CustomerService service;
    private final CategoryService categoryService;
    private final SubCategoryService subService;
    private final OrderService orderService;
    private final AddressService addressService;
    private final OfferService offerService;
    private final TransActionService transActionService;


    @GetMapping(value = "/customer/dashboard")
    public String dashboard(Principal principal,
                            Model model) {
        CustomerDto customerLogin = service.getByEmail(principal.getName());
        Map<String, Object> map = new HashMap<>();
        map.put("name", customerLogin.getFirstName());
        map.put("lastName", customerLogin.getLastName());
        map.put("role", customerLogin.getRole());
        map.put("creditCart", customerLogin.getCreditCart());
        map.put("userName", customerLogin.getUsername());
        map.put("password", customerLogin.getPassword());
        model.addAttribute("customer", customerLogin);
        model.addAllAttributes(map);
        return "customerPanel/profile";

    }


    @GetMapping(value = "/customer/editPass")
    public String editPass() {
        return "customerPanel/editPass";
    }


    @PostMapping(value = "/customer/editPass")
    public String editPassword(@ModelAttribute("customerDto") CustomerDto customerDto,
                               HttpServletRequest request
            , @RequestParam("newPass") String newPassword
            , @RequestParam("oldPass") String oldPassword) {
        if (customerDto.getPassword().equals(oldPassword)) {
            service.updatePassword(newPassword, oldPassword, customerDto);
            customerDto.setPassword(newPassword);
            request.getSession().setAttribute("customerDto", customerDto);
            return "redirect:/customer/dashboard";//todo how to save this customer with new password
        } else {
            return "error";
        }
    }

    @GetMapping(value = "/customer/editCredit")
    public String editCredit(HttpServletRequest request) {
        CustomerDto customer = (CustomerDto) request.getSession().getAttribute("customerDto");

        return "customerPanel/editCredit";
    }

    @PostMapping(value = "/customer/editCredit")
    public String editCreditCart(@SessionAttribute("customerDto") CustomerDto customerDto,
                                 @RequestParam("amount") Double amount,
                                 HttpServletRequest request) {
        service.updateCreditCart(amount + customerDto.getCreditCart(), customerDto);
        customerDto.setCreditCart(amount + customerDto.getCreditCart());
        request.getSession().setAttribute("customerDto", customerDto);
        return "redirect:/customer/dashboard";
    }

    @GetMapping(value = "/customer/categoryList")
    public String showCategoryList(Model model) {
        try {
            List<CategoryDto> list = categoryService.getAll();
            model.addAttribute("list", list);
        } catch (RuntimeException exception) {
            model.addAttribute("error", exception.getMessage());
        }
        return "customerPanel/categoryList";
    }

    @GetMapping("/customer/showSubCategory/{title}")
    public String showAllSubCategory(@PathVariable String title, Model model) {
        List<SubCategoryDto> list = subService.findAllSubCategoryOfACategory(title);
        model.addAttribute("list", list);
        return "customerPanel/viewSubCategory";
    }

    @GetMapping(value = "/customer/order/{title}")
    public ModelAndView makeOrder(@ModelAttribute("customerDto") CustomerDto dto
            , @PathVariable String title, HttpServletRequest request) {
        ModelAndView models = new ModelAndView();
        SubCategoryDto subForOrder = subService.findByTitle(title);
        request.getSession().setAttribute("subForOrder", subForOrder);
        models.addObject("address", new AddressDto());
        models.addObject("order", new OrdersDto());
        models.addObject("customerDto", dto);
        models.addObject("subForOrder", subForOrder);
        models.setViewName("customerPanel/orderForm");
        return models;
    }

    @PostMapping(value = "/customer/placeOrder")
    public String placeOrder(@RequestParam("city") String city,
                             @RequestParam("zipCode") String zipCode,
                             @RequestParam("street") String street,
                             @ModelAttribute("order") OrdersDto ordersDto,
                             HttpServletRequest request) {
        CustomerDto customerDto = (CustomerDto) request.getSession().getAttribute("customerDto");
        AddressDto addressDto = new AddressDto();
        addressDto.setCity(city);
        addressDto.setStreet(street);
        addressDto.setZipCode(zipCode);
        SubCategoryDto subForOrder = (SubCategoryDto) request.getSession().getAttribute("subForOrder");
        addressService.save(addressDto);
        orderService.save(ordersDto, customerDto, addressDto, subForOrder.getTitle());
        request.getSession().setAttribute("addressDto", addressDto);
        return "redirect:/customer/orderList";
    }

    @GetMapping(value = "/customer/orderList")
    public String orderList(HttpServletRequest request, Model model) {
        CustomerDto customerDto = (CustomerDto) request.getSession().getAttribute("customerDto");

        List<OrdersDto> orderOfCustomer = orderService.findOrderOfCustomer(customerDto)
                .stream().filter(ordersDto -> ordersDto.getState() != OrderState.PAID).collect(Collectors.toList());
        model.addAttribute("orderOfCustomer", orderOfCustomer);
        return "customerPanel/orderOfCustomer";

    }

    @GetMapping(value = "/customer/seeAllOfferAnOrder/{codeNumber}")
    public String seeAllOfferAnOrder(HttpServletRequest request,
                                     @PathVariable String codeNumber,
                                     Model model) {

        CustomerDto customerDto = (CustomerDto) request.getSession().getAttribute("customerDto");
        String sort = "expert.score";
        List<OfferDto> allOfferOfAnOrder = offerService.findAllOfferOfAnOrder(codeNumber, sort);
        if (allOfferOfAnOrder.size() == 0) {
            model.addAttribute("error", new NotFoundDta("no offer"));//todo
        } else {
            model.addAttribute("allOfferOfAnOrder", allOfferOfAnOrder);
        }
        model.addAttribute("codeNumber", codeNumber);
        return "customerPanel/allOfferOfAnOrder";
    }

    @GetMapping(value = "/customer/seeAllOfferAnOrders/{sort}")
    public String seeAllOfferAnOrderSort(@PathVariable String sort,
                                         Model model) {
        String codeNumber = (String) model.getAttribute("codeNumber");
        if (sort.equals("{proposedPrice}"))
            sort = "proposedPrice";
        else
            sort = "expert.score";
        List<OfferDto> allOfferOfAnOrder = offerService.findAllOfferOfAnOrder(codeNumber, sort);
        if (allOfferOfAnOrder.size() == 0) {
            model.addAttribute("error", new NotFoundDta("no offer"));//todo
        } else {
            model.addAttribute("allOfferOfAnOrder", allOfferOfAnOrder);
        }
        model.addAttribute("codeNumber", codeNumber);
        return "customerPanel/allOfferOfAnOrder";
    }

    @GetMapping(value = "/customer/acceptOffer/{codeNumber}")
    public String acceptAnOffer(@PathVariable String codeNumber) {
        offerService.acceptedOffer(codeNumber);//offer CodeNumber
        return "redirect:/customer/dashboard";
    }

    @GetMapping(value = "/customer/placeScore/{codeNumber}")
    public String placeScoreToOrderShow(Model model, @PathVariable String codeNumber) {
        model.addAttribute("codeNumber", codeNumber);
        //return "customerPages/offerStarted";
        return "customerPanel/ratingPage";
    }

    @PostMapping(value = "/customer/placeScore")//order codeNumber
    public String placeScoreToOrder(Model model,
                                    @RequestParam(value = "comment", required = false) String comment
            /*  ,@RequestParam(value = "rate") double score*/) {
        try {
            String codeNumber = (String) model.getAttribute("codeNumber");
            double score = 5.0;
            System.out.println(codeNumber);
            System.out.println(comment);
            //  System.out.println(score);
            orderService.placeScore(codeNumber, comment, score);//todo
            return "redirect:/customer/orderList";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "customerPanel/ratingPage";
        }
    }

   /* @GetMapping("/pay/{orderCodeNumber}")
    public String payOrder(@PathVariable String orderCodeNumber,
                                 HttpServletRequest request) {
        System.out.println(orderCodeNumber);
        //ModelAndView modelAndView = new ModelAndView();
        // modelAndView.addObject("orderCodeNumber", orderCodeNumber);
        request.getSession().setAttribute("orderCodeNumber", orderCodeNumber);
        // modelAndView.setViewName("customerPages/pay-with");
        // return modelAndView;
        return "customerPages/pay-with";
    }*/

    @GetMapping(value = "/customer/payOnline/{orderCodeNumber}")
    public ModelAndView payOnline(@PathVariable String orderCodeNumber) {
        ModelAndView modelAndView = new ModelAndView();
        OrdersDto orderByCodeNumber = orderService.findOrderByCodeNumber(orderCodeNumber);
        // modelAndView.setViewName("customerPages/payment");
        modelAndView.setViewName("customerPanel/payPage");
        modelAndView.addObject("transAction", new TransActionDto());
        modelAndView.addObject("orderCodeNumber", orderCodeNumber);
        System.out.println(orderByCodeNumber.getAgreedPrice());
        modelAndView.addObject("amount", orderByCodeNumber.getAgreedPrice());
        return modelAndView;
    }

    @PostMapping(value = "/customer/payWithOnline")
    public String payOrder(@ModelAttribute("transAction") TransActionDto transActionDto,
                           Model model) {
        String orderCodeNumber = (String) model.getAttribute("orderCodeNumber");
        try {
            OrdersDto foundOrder = orderService.findOrderByCodeNumber(orderCodeNumber);
            transActionService.save(transActionDto, foundOrder);
            model.addAttribute("message", "payment Successfully Done");
        } catch (RuntimeException exp) {
            model.addAttribute("error", exp.getMessage());
        }
        return "customerPanel/pay-online";
    }

    @GetMapping("/customer/payWithCreditCard/{orderCodeNumber}")
    public String payOrder(HttpServletRequest request, Model model,
                           @PathVariable String orderCodeNumber) {
        OrdersDto order = orderService.findOrderByCodeNumber(orderCodeNumber);
        try {
            transActionService.paidForOrder(orderCodeNumber);
            //  orderService.updateState(order, OrderState.PAID);
            model.addAttribute("message", "payment Successfully Done");
        } catch (RuntimeException exp) {
            model.addAttribute("error", exp.getMessage());
        }
        return "customerPanel/pay-with-credit";
    }

    @GetMapping(value = "/customer/orderHistory")
    public String orderHistory(HttpServletRequest request, Model model) {
        CustomerDto customerLogin = (CustomerDto) request.getSession().getAttribute("customerDto");
        List<OrdersDto> orderOfCustomer = orderService.findOrderOfCustomer(customerLogin);
        model.addAttribute("orderOfCustomer", orderOfCustomer);
        return "customerPanel/all-order-list";
    }


    @ExceptionHandler(value = BindException.class)
    public ModelAndView bindExceptionHandler(BindException ex, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        return new ModelAndView(lastView, ex.getBindingResult().getModel());
    }


    @ExceptionHandler(value = DuplicateData.class)
    public ModelAndView duplicateEmailExceptionHandler(DuplicateData ex, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        Map<String, Object> model = new HashMap<>();
        model.put("error", ex.getMessage());
        model.put("customer", new CustomerDto());
        return new ModelAndView(lastView, model);
    }

    @ExceptionHandler(value = NoOffer.class)
    public ModelAndView noOfferExceptionHandler(NoOffer ex) {
        Map<String, Object> model = new HashMap<>();
        model.put("error", ex.getMessage());
        return new ModelAndView("customerPanel/orderList", model);
    }

    @ExceptionHandler(value = LessAmount.class)
    public ModelAndView lessAmountExceptionHandler(LessAmount ex) {
        Map<String, Object> model = new HashMap<>();
        model.put("order", new OrdersDto());
        model.put("address", new AddressDto());
        model.put("error", ex.getMessage());
        return new ModelAndView("customerPanel/orderForm", model);
    }

    @ExceptionHandler(value = CustomerNotFoundException.class)
    public ModelAndView loginExceptionHandler(CustomerNotFoundException ex) {
        Map<String, Object> model = new HashMap<>();
        model.put("customer", new CustomerDto());
        model.put("error", ex.getMessage());
        return new ModelAndView("login", model);
    }

}
