package course.spring.dao;

import course.spring.entity.Anecdote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnecdoteRepository extends JpaRepository<Anecdote, Long> {
}