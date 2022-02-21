
package ir.maktab.homeserviceprovider.web;


import ir.maktab.configuration.LastViewInterceptor;
import ir.maktab.dto.CategoryDto;
import ir.maktab.service.CategoryServiceImpl;
import ir.maktab.service.exception.DuplicateData;
import ir.maktab.service.exception.NoCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class CategoryController {
    private final CategoryServiceImpl categoryService;

    @PostMapping("/admin/saveCategory")
    public String saveNewCategory(CategoryDto categoryDto, HttpServletRequest request, Model model) {
        Object admin = request.getSession().getAttribute("admin");
        if (admin == null)
            return "error";
        categoryService.save(categoryDto);
        return "redirect:/admin/mangeCategory";
    }

    @GetMapping("/admin/categoryForm")
    public String showCategoryForm(Model m, HttpServletRequest request) {
        Object admin = request.getSession().getAttribute("admin");
        if (admin == null)
            return "error";
        m.addAttribute("category", new CategoryDto());
        return "adminPanel/categoryForm";
    }

    @ExceptionHandler(value = BindException.class)
    public ModelAndView bindExceptionHandler(BindException ex, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        return new ModelAndView(lastView, ex.getBindingResult().getModel());
    }

   @ExceptionHandler(value = NoCategory.class)
    public ModelAndView NoTitleExceptionHandler(NoCategory ex) {
        Map<String, Object> model = new HashMap<>();
        model.put("category", new CategoryDto());
        model.put("error", ex.getMessage());
        return new ModelAndView("adminPanel/categoryForm", model);
    }

    @ExceptionHandler(value = DuplicateData.class)
    public ModelAndView saveDuplicateExceptionHandler(DuplicateData ex) {
        Map<String, Object> model = new HashMap<>();
        model.put("category", new CategoryDto());
        model.put("error", ex.getMessage());
        return new ModelAndView("adminPanel/categoryForm", model);
    }
}
