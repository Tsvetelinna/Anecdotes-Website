package course.spring.web;

import course.spring.entity.Category;
import course.spring.entity.User;
import course.spring.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public String getCategories(Model model) {
        setModel(model);
        return "categories";
    }

    @GetMapping("/categories-admin")
    public String getCategoriesAdmin(Model model) {
        setModel(model);
        return "categories-admin";
    }

    @GetMapping("/categories-user")
    public String getCategoriesUser(Model model) {
        setModel(model);
        return "categories-user";
    }

    @GetMapping("/add-category")
    public String addCategory(Category category) {
        return "add-category";
    }

    @PostMapping("/categories/add")
    public String signUp(Category category) {
        categoryService.addCategory(category);
        return "redirect:/categories-admin";
    }

    private void setModel(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("path", "categories");
    }
}
