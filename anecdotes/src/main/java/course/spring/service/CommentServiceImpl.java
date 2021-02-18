package course.spring.service;

import course.spring.dao.CommentRepository;
import course.spring.entity.*;
import course.spring.exception.NonExisitingEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Validated
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private AnecdoteService anecdoteService;

    @Override
    public List<CommentInfo> getAllCommentsByAnecdote(Long anecdoteId) {
        return commentRepository.findByAnecdoteId(anecdoteId).stream().map(Comment::toCommentInfo).collect(Collectors.toList());
    }

    @Override
    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElseThrow(() ->
                new NonExisitingEntityException(String.format("Comment with ID='%d' does not exists", id)));
    }

    @Override
    public Comment addComment(Comment comment) {
        comment.setId(null);
        return commentRepository.save(comment);    }

    @Override
    public Comment addComment(CommentInfo commentInfo) {
        final Comment comment = Comment.fromCommentInfo(commentInfo);
        final User user = getAuthenticatedUser();
        comment.setAuthor(user);
        final Anecdote anecdote = anecdoteService.getAnecdoteById(commentInfo.getAnecdoteId());
        comment.setAnecdote(anecdote);
        Comment save = commentRepository.save(comment);

        user.getComments().add(save);
        userService.updateUser(user);

        anecdote.getComments().add(save);
        anecdoteService.updateAnecdote(anecdote);
        return save;
    }

    @Override
    public Comment updateComment(Comment comment) {
        getCommentById(comment.getId());
        return commentRepository.save(comment);    }

    @Override
    public Comment deleteComment(Long id) {
        Comment deleted = getCommentById(id);
//        final Category category = categoryService.getCategoryById(deleted.getCategory().getId());
//        category.getAnecdotes().remove(deleted);
//        categoryService.updateCategory(category);
//
//        final User user = userService.getUserById(deleted.getAuthor().getId());
//        user.getAnecdotes().remove(deleted);
//        userService.updateUser(user);
        commentRepository.deleteById(id);
        return deleted;
    }

    private User getAuthenticatedUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.getUserByUsername(username);
    }
}
