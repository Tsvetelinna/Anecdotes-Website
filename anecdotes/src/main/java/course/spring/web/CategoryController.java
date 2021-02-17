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
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.server.PathParam;

import static course.spring.entity.Role.USER;

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

    @GetMapping("/update-categories")
    public String updateCategories(Model model) {
        setModel(model);
        return "update-categories";
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
    public String createCategory(Category category) {
        categoryService.addCategory(category);
        return "redirect:/categories-admin";
    }

    @GetMapping("/update-category")
    public String updateUser(@RequestParam("id") Long id, Model model) {
        model.addAttribute("category", categoryService.getCategoryById(id));
        return "update-category";
    }

    @PostMapping("/categories/update/{id}")
    public String update(@PathParam("id") Long id, Category category) {
        category.setId(id);
        categoryService.updateCategory(category);
        return "redirect:/categories-admin";
    }

    @GetMapping("/categories/delete")
    public String delete(@RequestParam("id") Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories-admin";
    }

    private void setModel(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("path", "categories");
    }
}
