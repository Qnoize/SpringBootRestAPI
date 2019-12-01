package ru.jmentor.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.jmentor.model.Role;
import ru.jmentor.model.Status;
import ru.jmentor.model.User;
import ru.jmentor.repository.RoleRepository;
import ru.jmentor.repository.UserRepository;
import ru.jmentor.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepository.saveAndFlush(user);

        log.info("IN register - user: {} successfully registered", registeredUser);

        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("IN getAll - {} user found", result.size());
        return result;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        log.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public User findById(Long id){
        User result = userRepository.findById(id).orElse(null);

        if(result == null){
            log.warn("IN findById - no user found by id: {}", id);
        }

        log.info("IN findById - user: {} found by id: {}", result, id);
        return result;
    }

    @Override
    @Transactional
    public Long delete(Long id){
        userRepository.deleteById(id);
        log.info("IN delete - user with id: {} successfully deleted", id);
        return id;
    }

    @Override
    public User edit(User user){
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);

        User editeddUser = userRepository.saveAndFlush(user);

        log.info("IN register - user: {} successfully registered", editeddUser);

        return editeddUser;
    }

    public boolean userExistByUsername(String username) {
         if(userRepository.isExistByUserName(username) != null){
             log.info("IN userExistByUsername - user: {} isExist", username);
             return true;
         }
        log.info("IN userExistByUsername - user: {} is NOT Exist", username);
        return false;
    }

}
