package course.spring.web;

import course.spring.entity.Anecdote;
import course.spring.entity.AnecdoteInfo;
import course.spring.service.AnecdoteService;
import course.spring.service.CategoryService;
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

@Controller
@RequestMapping
public class AnecdoteController {

    private static final String UPLOADS_DIR = "anecdotes-images";
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

    @GetMapping("/my-anecdotes")
    public String getMyAnecdotes(Model model) {
        model.addAttribute("anecdotes", anecdoteService.getAllAnecdotesByAuthor());
        model.addAttribute("path", "my-anecdotes");
        return "my-anecdotes";
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

    @GetMapping("/by-category")
    public String getAllAnecdotesByCategoryAdmin(@RequestParam("id") Long categoryId, Model model) {
        model.addAttribute("anecdotes", anecdoteService.getAllAnecdotesByCategory(categoryId));
        model.addAttribute("path", "categories");
        return "admin-anecdotes-by-category";
    }

    @GetMapping("/add-anecdote")
    public String addAnecdote(AnecdoteInfo anecdote, Model model) {
        model.addAttribute("anecdote", anecdote);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("path", "categories");
        return "add-anecdote";
    }

    @PostMapping("/anecdotes/add")
    public String createAnecdote(AnecdoteInfo anecdote, @RequestParam("image") MultipartFile file) {
        if (file != null) {
            handleMultipartFile(file);
            anecdote.setPicture(file.getOriginalFilename());
        }
        anecdoteService.addAnecdote(anecdote);
        return "redirect:/anecdotes-user";
    }

    @GetMapping("/update-user-anecdote")
    public String updateUserAnecdotes(@RequestParam("id") Long id, Model model) {
        final Anecdote anecdote = anecdoteService.getAnecdoteById(id);
        final AnecdoteInfo anecdoteInfo = new AnecdoteInfo(id, anecdote.getText(), anecdote.getPicture(), anecdote.getCategory().getId());
        model.addAttribute("anecdote", anecdoteInfo);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "update-user-anecdote";
    }

    @PostMapping("/users/anecdotes/update/{id}")
    public String updateUserAnecdote(@PathParam("id") Long id, AnecdoteInfo anecdoteInfo, @RequestParam("image") MultipartFile file) {
        if (file != null) {
            handleMultipartFile(file);
            anecdoteInfo.setPicture(file.getOriginalFilename());
        }
        anecdoteService.updateAnecdote(id, anecdoteInfo);
        return "redirect:/my-anecdotes";
    }

    @GetMapping("/users/anecdotes/delete")
    public String deleteUserAnecdote(@RequestParam("id") Long id) {
        anecdoteService.deleteAnecdote(id);
        return "redirect:/my-anecdotes";
    }

    @GetMapping("/update-anecdote")
    public String updateAdminAnecdotes(@RequestParam("id") Long id, Model model) {
        final Anecdote anecdote = anecdoteService.getAnecdoteById(id);
        final AnecdoteInfo anecdoteInfo = new AnecdoteInfo(id, anecdote.getText(), anecdote.getPicture(), anecdote.getCategory().getId());
        model.addAttribute("anecdote", anecdoteInfo);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "update-anecdote";
    }

    @PostMapping("/admins/anecdotes/update/{id}")
    public String updateAdminAnecdote(@PathParam("id") Long id, AnecdoteInfo anecdoteInfo, @RequestParam("image") MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            handleMultipartFile(file);
            anecdoteInfo.setPicture(file.getOriginalFilename());
        }
        anecdoteService.updateAnecdote(id, anecdoteInfo);
        return "redirect:/anecdotes-admin";
    }

    @GetMapping("/admins/anecdotes/delete")
    public String deleteAdminAnecdote(@RequestParam("id") Long id) {
        anecdoteService.deleteAnecdote(id);
        return "redirect:/anecdotes-admin";
    }

    private void setModel(Model model) {
        model.addAttribute("anecdotes", anecdoteService.getAllAnecdotes());
        model.addAttribute("path", "categories");
    }

    private void handleMultipartFile(MultipartFile file) {
        try {
            File currentDir = new File(UPLOADS_DIR);
            if(!currentDir.exists()) {
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
