package ir.maktab.homeserviceprovider.web;

import ir.maktab.configuration.LastViewInterceptor;
import ir.maktab.dto.CategoryDto;
import ir.maktab.dto.SubCategoryDto;
import ir.maktab.service.interfaces.CategoryService;
import ir.maktab.service.interfaces.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class SubCategoryController {
    private final SubCategoryService subCategoryService;
    private final CategoryService categoryService;

    @GetMapping("/admin/addSubCategory/{title}")
    public String showAddSubServicePage(@PathVariable String title, Model model,
                                        HttpServletRequest httpServletRequest) {
        model.addAttribute("subCategoryDto", new SubCategoryDto());
        httpServletRequest.getSession().setAttribute("mainCategoryName", title);
        return "adminPanel/saveSubCategory";
    }

    @PostMapping("/admin/addSubCategory/saveSubCategory")
    public String submitSaveSubServicePage(@ModelAttribute("subCategoryDto") SubCategoryDto subCategoryDto,
                                           HttpServletRequest httpServletRequest,
                                           Model model) {
        String title = (String) httpServletRequest.getSession().getAttribute("mainCategoryName");
        try {
            subCategoryService.save(subCategoryDto, title);
            return "redirect:/admin/mangeCategory";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "adminPanel/saveSubCategory";
        }
    }

    @GetMapping("/admin/list")//adminDashboard?name=neda
    public String showSubForm(Model model, HttpServletRequest request) {
        model.addAttribute("subCategoryDto", new SubCategoryDto());
        try {
            List<CategoryDto> listCategory = categoryService.getAll();
            request.setAttribute("listCategory", listCategory);
        } catch (RuntimeException e) {
            model.addAttribute("error", "something wrong happen");
        }
        return "adminPanel/addingSubForm";
    }

    @PostMapping("/admin/list")
    public String addSubService(HttpServletRequest request, Model model,
                                @ModelAttribute("subCategoryDto") SubCategoryDto subCategoryDto) {
        String categoryTitle = request.getParameter("category");

        subCategoryService.save(subCategoryDto, categoryTitle);
        request.setAttribute("categoryTitle", categoryTitle);
        model.addAttribute("message", "subCategory successfully added");

        return "adminPanel/saveSubCategory";
    }

    @GetMapping(value = "/admin/edit-subcategory/{title}")
    public String editSub(@PathVariable("title") String title,
                          HttpServletRequest request, Model model) {
        request.getSession().setAttribute("subTitle", title);
        //request.getSession().setAttribute("subCategoryDto", new SubCategoryDto());
        model.addAttribute("subCategoryDto", new SubCategoryDto());
        return "adminPanel/editSubForm";
    }

    @PostMapping(value = "/admin/edit-subcategory")
    public String editSub(@RequestParam(value = "title", required = false) String title,
                          @RequestParam(value = "basePrice", required = false) double basePrice,
                          @RequestParam(value = "description", required = false) String description,
                          HttpServletRequest request) {
        String subTitle = (String) request.getAttribute("subTitle");
        if (title.isBlank())
            title = subTitle;
        subCategoryService.update(title, basePrice, description);
        return "redirect:/admin/dashboard";
    }

    @GetMapping(value = "/admin/showingSubCategory")
    public String showingSubCategory(Model model, HttpServletRequest request) {
        List<CategoryDto> listCategory = categoryService.getAll();
        request.setAttribute("categoryList", listCategory);
        model.addAttribute("categoryList", listCategory);
        return "adminPanel/showing-sub-category";
    }

    @ExceptionHandler(value = BindException.class)
    public ModelAndView bindExceptionHandler(BindException ex, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        return new ModelAndView(lastView, ex.getBindingResult().getModel());
    }

}
