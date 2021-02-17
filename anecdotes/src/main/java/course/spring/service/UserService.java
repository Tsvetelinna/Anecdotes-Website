package course.spring.service;

import course.spring.entity.Role;
import course.spring.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    List<User> getAllUsers(Role role);

    User getUserById(Long id);

    User getUserProfile();

    User getUserByUsername(String username);

    User addUser(User user);

    User updateUser(User user);

    User deleteUser(Long id);

    long getUsersCount();
}
