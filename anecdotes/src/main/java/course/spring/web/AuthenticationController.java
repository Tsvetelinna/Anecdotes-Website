package course.spring.web;

import course.spring.entity.User;
import course.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static course.spring.entity.Role.USER;

@Controller
@RequestMapping("")
public class AuthenticationController {

    private static final String UPLOADS_DIR = "users-images";
    private UserService userService;

    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/register")
    public String register(User user) {
        return "register";
    }

    @PostMapping("/sign-up")
    public String signUp(User user, @RequestParam("image") MultipartFile file) {
        if (file != null) {
            handleMultipartFile(file);
            user.setProfilePicture(file.getOriginalFilename());
        }
        user.setRole(USER);
        userService.addUser(user);
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
