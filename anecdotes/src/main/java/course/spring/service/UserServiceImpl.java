package course.spring.service;

import course.spring.dao.UserRepository;
import course.spring.entity.User;
import course.spring.exception.InvalidEntityDataException;
import course.spring.exception.NonExisitingEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static course.spring.entity.Role.USER;

@Service
@Transactional(readOnly = true)
@Validated
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new NonExisitingEntityException(
                        String.format("User with ID='%d' does not exists", id)));
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
        getUserById(user.getId());
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
}
