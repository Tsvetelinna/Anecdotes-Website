package course.spring.service;

import course.spring.entity.Anecdote;
import course.spring.entity.AnecdoteInfo;

import java.util.List;

public interface AnecdoteService {

    List<Anecdote> getAllAnecdotes();

    List<Anecdote> getAllAnecdotesByCategory(Long categoryId);

    List<Anecdote> getAllAnecdotesByAuthor();

    Anecdote getAnecdoteById(Long id);

    Anecdote addAnecdote(Anecdote anecdote);

    Anecdote addAnecdote(AnecdoteInfo anecdote);

    Anecdote updateAnecdote(Anecdote anecdote);

    Anecdote updateAnecdote(Long id, AnecdoteInfo anecdote);

    Anecdote deleteAnecdote(Long id);

    long getAnecdotesCount();

}
