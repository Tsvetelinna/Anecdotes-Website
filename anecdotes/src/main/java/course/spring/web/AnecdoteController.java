package course.spring.web;

import course.spring.dao.CategoryRepository;
import course.spring.entity.Anecdote;
import course.spring.entity.AnecdoteInfo;
import course.spring.entity.Category;
import course.spring.service.AnecdoteService;
import course.spring.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class AnecdoteController {

    private AnecdoteService anecdoteService;
    private CategoryService categoryService;

    @Autowired
    public AnecdoteController(AnecdoteService anecdoteService, CategoryService categoryService) {
        this.anecdoteService = anecdoteService;
        this.categoryService = categoryService;
    }

    @GetMapping("/anecdotes")
    public String getAnecdotes(Model model) {
        setModel(model);
        return "anecdotes";
    }

    @GetMapping("/anecdotes-admin")
    public String getAnecdotesAdmin(Model model) {
        setModel(model);
        return "anecdotes-admin";
    }

    @GetMapping("/anecdotes-user")
    public String getAnecdotesUser(Model model) {
        setModel(model);
        return "anecdotes-user";
    }

    @GetMapping("/anecdotes/by-category")
    public String getAllAnecdotesByCategory(@RequestParam("id") Long categoryId, Model model) {
        model.addAttribute("anecdotes", anecdoteService.getAllAnecdotesByCategory(categoryId));
        model.addAttribute("path", "categories");
        return "anecdotes-by-category";
    }

    @GetMapping("/users/anecdotes/by-category")
    public String getAllAnecdotesByCategoryUser(@RequestParam("id") Long categoryId, Model model) {
        model.addAttribute("anecdotes", anecdoteService.getAllAnecdotesByCategory(categoryId));
        model.addAttribute("path", "categories");
        return "users-anecdotes-by-category";
    }

    @GetMapping("/add-anecdote")
    public String addAnecdote(AnecdoteInfo anecdote, Model model) {
        model.addAttribute("anecdote", anecdote);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("path", "categories");
        return "add-anecdote";
    }

    @PostMapping("/anecdotes/add")
    public String createAnecdote(AnecdoteInfo anecdote) {
        anecdoteService.addAnecdote(anecdote);
        return "redirect:/anecdotes-user";
    }


    private void setModel(Model model) {
        model.addAttribute("anecdotes", anecdoteService.getAllAnecdotes());
        model.addAttribute("path", "categories");
    }
}
