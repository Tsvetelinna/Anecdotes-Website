package course.spring.web;

import course.spring.service.AnecdoteService;
import course.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class AnecdoteController {

    private AnecdoteService anecdoteService;

    @Autowired
    public AnecdoteController(AnecdoteService anecdoteService) {
        this.anecdoteService = anecdoteService;
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

    private void setModel(Model model) {
        model.addAttribute("anecdotes", anecdoteService.getAllAnecdotes());
        model.addAttribute("path", "categories");
    }
}
