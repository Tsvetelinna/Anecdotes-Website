package course.spring.web;

import course.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/users")
public class AuthenticationController {

    private UserService userService;

    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String getPosts(Model model) {
        model.addAttribute("path", "projects");
        return "projects";
    }

    @GetMapping("/home")
    public String getHome(@RequestParam("name") String name, Model model) {
        System.out.println(name);
        model.addAttribute("name", name);
        model.addAttribute("path", "home");
        return "home";
    }
}
