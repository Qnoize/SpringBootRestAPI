package ru.jmentor.service;

import ru.jmentor.model.User;
import java.util.List;

public interface UserService {

    List<User> getAll();

    User findById(Long id);

    Long delete(Long id);

    User edit(User user);

    User findByUsername(String username);

    User register(User user);
}
