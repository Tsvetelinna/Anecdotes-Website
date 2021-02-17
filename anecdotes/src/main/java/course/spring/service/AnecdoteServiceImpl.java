package course.spring.service;

import course.spring.dao.AnecdoteRepository;
import course.spring.entity.Anecdote;
import course.spring.entity.AnecdoteInfo;
import course.spring.entity.Category;
import course.spring.entity.User;
import course.spring.exception.NonExisitingEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Transactional
@Validated
public class AnecdoteServiceImpl implements AnecdoteService {

    @Autowired
    private AnecdoteRepository anecdoteRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Override
    public List<Anecdote> getAllAnecdotes() {
        return anecdoteRepository.findAll();
    }

    @Override
    public List<Anecdote> getAllAnecdotesByCategory(Long categoryId) {
        return anecdoteRepository.findByCategoryId(categoryId);
    }

    @Override
    public List<Anecdote> getAllAnecdotesByAuthor() {
        Long userId = getAuthenticatedUser().getId();
        return anecdoteRepository.findByAuthorId(userId);
    }

    @Override
    public Anecdote getAnecdoteById(Long id) {
        return anecdoteRepository.findById(id).orElseThrow(() ->
                new NonExisitingEntityException(String.format("Anecdote with ID='%d' does not exists", id)));
    }

    @Override
    public Anecdote addAnecdote(Anecdote anecdote) {
        anecdote.setId(null);
        return anecdoteRepository.save(anecdote);
    }

    @Override
    public Anecdote addAnecdote(AnecdoteInfo info) {
        final Category category = categoryService.getCategoryById(info.getCategoryId());
        final User author = getAuthenticatedUser();
        Anecdote anecdote = new Anecdote(info.getDescription(), info.getPicture(), author, category);
        anecdote.setId(null);
        Anecdote save = anecdoteRepository.save(anecdote);

        category.getAnecdotes().add(save);
        categoryService.updateCategory(category);
        author.getAnecdotes().add(save);
        userService.updateUser(author);
        return save;
    }

    @Override
    public Anecdote updateAnecdote(Anecdote anecdote) {
        getAnecdoteById(anecdote.getId());
        return anecdoteRepository.save(anecdote);
    }

    @Override
    public Anecdote updateAnecdote(Long id, AnecdoteInfo info) {
        final Anecdote anecdote = getAnecdoteById(id);
        anecdote.setText(info.getDescription());
        anecdote.setPicture(info.getPicture());
        final Category oldCategory = anecdote.getCategory();
        Category newCategory = new Category();
        boolean hasNewCategory = false;

        if (!oldCategory.getId().equals(info.getCategoryId())) {
            newCategory = categoryService.getCategoryById(info.getCategoryId());
            anecdote.setCategory(newCategory);
            hasNewCategory = true;
        }

        Anecdote save = anecdoteRepository.save(anecdote);
        if (hasNewCategory) {
            oldCategory.getAnecdotes().remove(save);
            newCategory.getAnecdotes().add(save);
        }
        return save;
    }

    @Override
    public Anecdote deleteAnecdote(Long id) {
        Anecdote deleted = getAnecdoteById(id);
        final Category category = categoryService.getCategoryById(deleted.getCategory().getId());
        category.getAnecdotes().remove(deleted);
        categoryService.updateCategory(category);

        final User user = userService.getUserById(deleted.getAuthor().getId());
        user.getAnecdotes().remove(deleted);
        userService.updateUser(user);
        anecdoteRepository.deleteById(id);
        return deleted;
    }

    @Override
    public long getAnecdotesCount() {
        return anecdoteRepository.count();
    }

    private User getAuthenticatedUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.getUserByUsername(username);
    }
}
