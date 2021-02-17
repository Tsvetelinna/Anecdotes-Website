package course.spring.service;

import course.spring.dao.UserRepository;
import course.spring.entity.Role;
import course.spring.entity.User;
import course.spring.exception.InvalidEntityDataException;
import course.spring.exception.NonExisitingEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@Validated
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getAllUsers(Role role) {
        return userRepository.findAllByRole(role.toString());
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new NonExisitingEntityException(
                        String.format("User with ID='%d' does not exists", id)));
    }

    @Override
    public User getUserProfile() {
        return getAuthenticatedUser();
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new InvalidEntityDataException("Ivalid username or password."));
    }

    @Override
    @Transactional
    public User addUser(User user) {
        user.setId(null);
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        User userById = getUserById(user.getId());
        user.setPassword(userById.getPassword());
        user.setUpdatedAt(Date.from(Instant.now()));
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User deleteUser(Long id) {
        User deleted = getUserById(id);
        userRepository.deleteById(id);
        return deleted;
    }

    @Override
    public long getUsersCount() {
        return userRepository.count();
    }

    private User getAuthenticatedUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUserByUsername(username);
    }
}
