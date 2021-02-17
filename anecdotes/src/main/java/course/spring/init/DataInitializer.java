package course.spring.init;

import course.spring.dao.UserRepository;
import course.spring.entity.Anecdote;
import course.spring.entity.Category;
import course.spring.entity.Role;
import course.spring.entity.User;
import course.spring.service.AnecdoteService;
import course.spring.service.CategoryService;
import course.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    public static final List<User> users = List.of(new User("Default", "admin@mail.com", "admin123", Role.ADMIN, null));
    public static final List<Category> categories = List.of(new Category("Политически"));
    public static final List<Anecdote> anecdotes = List.of(
            new Anecdote("Предизборен митинг. – Гарантираме, че само след пет години всички ние ще живеем по-добре, " +
                    "отколкото в Европа! Уплашен глас от последния ред: – Какво ли ще им се случи на горките хора там?",
                    null, users.get(0), categories.get(0)));

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AnecdoteService anecdoteService;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        if (userService.getUsersCount() == 0) {
            users.forEach(userService::addUser);
        }

        if (categoryService.getCategoriesCount() == 0) {
            categories.forEach(categoryService::addCategory);
        }

        if (anecdoteService.getAnecdotesCount() == 0) {
            anecdotes.forEach(anecdote -> anecdoteService.addAnecdote(anecdote));
            anecdotes.forEach(a -> a.getCategory().getAnecdotes().add(a));
            anecdotes.forEach(a -> a.getAuthor().getAnecdotes().add(a));
        }
    }
}
