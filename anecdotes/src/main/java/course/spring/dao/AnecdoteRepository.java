package course.spring.dao;

import course.spring.entity.Anecdote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnecdoteRepository extends JpaRepository<Anecdote, Long> {

    List<Anecdote> findByCategoryId(Long categoryId);

    List<Anecdote> findByAuthorId(Long authorId);

}