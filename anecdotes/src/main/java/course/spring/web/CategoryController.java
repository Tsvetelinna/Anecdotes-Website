package course.spring.web;

import course.spring.service.AnecdoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/anecdotes")
public class CategoryController {

    private AnecdoteService anecdoteService;

    @Autowired
    public CategoryController(AnecdoteService anecdoteService) {
        this.anecdoteService = anecdoteService;
    }

    @GetMapping
    public String getAnecdotes(Model model) {
        model.addAttribute("path", "anecdotes");
        return "anecdotes";
    }
}
