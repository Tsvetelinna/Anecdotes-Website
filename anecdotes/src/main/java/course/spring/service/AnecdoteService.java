package course.spring.service;

import course.spring.entity.Anecdote;

import java.util.List;

public interface AnecdoteService {

    List<Anecdote> getAllAnecdotes();

    Anecdote getAnecdoteById(Long id);

    Anecdote addAnecdote(Anecdote anecdote);

    Anecdote updateAnecdote(Anecdote anecdote);

    Anecdote deleteAnecdote(Long id);
}
