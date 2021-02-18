package course.spring.web;

import course.spring.entity.User;
import course.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static course.spring.entity.Role.ADMIN;
import static course.spring.entity.Role.USER;


@Controller
@RequestMapping
public class UserController {

    private static final String UPLOADS_DIR = "users-images";
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
    public String update(@PathParam("id") Long id, User user, @RequestParam("image") MultipartFile file) {
        if (file != null) {
            handleMultipartFile(file);
            user.setProfilePicture(file.getOriginalFilename());
        }
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
    public String editUserProfile(@PathParam("id") Long id, User user, @RequestParam("image") MultipartFile file) {
        if (file != null) {
            handleMultipartFile(file);
            user.setProfilePicture(file.getOriginalFilename());
        }
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
    public String editAdminProfile(@PathParam("id") Long id, User user, @RequestParam("image") MultipartFile file) {
        if (file != null) {
            handleMultipartFile(file);
            user.setProfilePicture(file.getOriginalFilename());
        }

        user.setId(id);
        user.setRole(ADMIN);
        userService.updateUser(user);
        return "redirect:/profile-admin";
    }

    private void handleMultipartFile(MultipartFile file) {
        try {
            File currentDir = new File(UPLOADS_DIR);
            if (!currentDir.exists()) {
                currentDir.mkdirs();
            }
            String path = currentDir.getAbsolutePath() + "/" + file.getOriginalFilename();
            path = new File(path).getAbsolutePath();
            File f = new File(path);
            FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(f));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
