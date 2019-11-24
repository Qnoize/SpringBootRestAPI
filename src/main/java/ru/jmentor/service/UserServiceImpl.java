package ru.jmentor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.jmentor.model.Role;
import ru.jmentor.model.User;
import ru.jmentor.repository.UserRepository;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder) { this.passwordEncoder = passwordEncoder; }

    @Autowired
    public void setRepository(UserRepository userRepository) { this.userRepository = userRepository; }

    @Override
    public List<User> getAllUsers(){ return userRepository.findAll(); }

    @Override
    public User getById(Long id){
        User user = userRepository.getById(id);
        user.setPassword(passwordEncoder.encode(user.setPassword()));
        return user;
    }

    @Override
    @Transactional
    public void deleteUserById(Long id){ userRepository.deleteById(id); }

    @Override
    public void editUser(User user){
        user.setPassword(passwordEncoder.encode(user.setPassword()));
        user.setRole(Collections.singleton(new Role(1L, "ROLE_USER")));
        userRepository.save(user);
    }

    @Override
    public void saveUser(User user){
        if(!userExistByLogin(user.getLogin())){
            user.setPassword(passwordEncoder.encode(user.setPassword()));
            user.setRole(Collections.singleton(new Role(1L, "ROLE_USER")));
            userRepository.saveAndFlush(user);
        }
    }

    public boolean userExistByLogin(String login) { return userRepository.isExistByLogin(login) != null; }

    @Override
    public User getByLogin(String login) {
        User user = userRepository.findByUserLogin(login);
        user.setPassword(passwordEncoder.encode(user.setPassword()));
        return user;
    }
}
