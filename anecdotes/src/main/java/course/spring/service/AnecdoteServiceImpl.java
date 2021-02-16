package course.spring.service;

import course.spring.dao.AnecdoteRepository;
import course.spring.entity.Anecdote;
import course.spring.exception.NonExisitingEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Transactional(readOnly = true)
@Validated
public class AnecdoteServiceImpl implements AnecdoteService {

    @Autowired
    private AnecdoteRepository anecdoteRepository;

    @Override
    public List<Anecdote> getAllAnecdotes() {
        return anecdoteRepository.findAll();
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
    public Anecdote updateAnecdote(Anecdote anecdote) {
        getAnecdoteById(anecdote.getId());
        return anecdoteRepository.save(anecdote);
    }

    @Override
    public Anecdote deleteAnecdote(Long id) {
        Anecdote deleted = getAnecdoteById(id);
        anecdoteRepository.deleteById(id);
        return deleted;
    }
}
