package course.spring.service;

import course.spring.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Long id);

    User getUserByUsername(String username);

    User addUser(User user);

    User updateUser(User user);

    User deleteUser(Long id);

    long getUsersCount();
}
