package course.spring.web;

import course.spring.entity.User;
import course.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static course.spring.entity.Role.*;

@Controller
@RequestMapping("")
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

    @GetMapping("/register")
    public String register(User user) {
        return "register";
    }

    @PostMapping("/sign-up")
    public String signUp(User user) {
        user.setRole(USER);
        User created = userService.addUser(user);
//        model.addAttribute("user", created);
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String getHome() {
        return "home-user";
    }

    @GetMapping("/admin/home")
    public String getHomeAdmin() {
        return "home-admin";
    }

}
