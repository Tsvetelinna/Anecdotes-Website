package course.spring.service;

import course.spring.entity.Anecdote;
import course.spring.entity.AnecdoteInfo;
import course.spring.entity.Comment;
import course.spring.entity.CommentInfo;

import java.util.List;

public interface CommentService {

    List<CommentInfo> getAllCommentsByAnecdote(Long anecdoteId);

    Comment getCommentById(Long id);

    Comment addComment(Comment comment);

    Comment addComment(CommentInfo commentInfo);

    Comment updateComment(Comment comment);

    Comment deleteComment(Long id);


}
