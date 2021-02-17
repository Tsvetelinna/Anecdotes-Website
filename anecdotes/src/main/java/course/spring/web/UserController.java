package course.spring.web;

import course.spring.entity.Category;
import course.spring.entity.Role;
import course.spring.entity.User;
import course.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

import static course.spring.entity.Role.ADMIN;
import static course.spring.entity.Role.USER;


@Controller
@RequestMapping
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    public String getCategoriesUser(Model model) {
        model.addAttribute("users", userService.getAllUsers(USER));
        model.addAttribute("path", "users");
        return "users";
    }

    @GetMapping("/update-user")
    public String updateUser(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "update-user";
    }

    @PostMapping("/users/update/{id}")
    public String update(@PathParam("id") Long id, User user) {
        user.setId(id);
        user.setRole(USER);
        userService.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping("/users/delete")
    public String delete(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("/profile-user")
    public String getUserProfile(Model model) {
        model.addAttribute("user", userService.getUserProfile());
        return "profile-user";
    }

    @GetMapping("/update-user-profile")
    public String updateUserProfile(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "update-user-profile";
    }

    @PostMapping("/profile/update/{id}")
    public String editUserProfile(@PathParam("id") Long id, User user) {
        user.setId(id);
        user.setRole(USER);
        userService.updateUser(user);
        return "redirect:/profile-user";
    }

    @GetMapping("/profile-admin")
    public String getAdminProfile(Model model) {
        model.addAttribute("user", userService.getUserProfile());
        return "profile-admin";
    }

    @GetMapping("/update-admin-profile")
    public String updateAdminProfile(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "update-admin-profile";
    }

    @PostMapping("/profile-admin/update/{id}")
    public String editAdminProfile(@PathParam("id") Long id, User user) {
        user.setId(id);
        user.setRole(ADMIN);
        userService.updateUser(user);
        return "redirect:/profile-admin";
    }

}
