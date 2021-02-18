package course.spring.dao;

import course.spring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value =
            " select * " +
                    " from users u " +
                    " where u.role = :role " +
                    " order by u.created_at ASC", nativeQuery = true
    )
    List<User> findAllByRole(@Param("role") String role);

    Optional<User> findByUsername(String username);
}