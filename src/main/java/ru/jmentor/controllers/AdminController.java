package ru.jmentor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.jmentor.model.User;
import ru.jmentor.service.UserService;
import java.util.List;

@RestController
public class AdminController {

    private UserService service;

    @Autowired
    public void setService(UserService service) { this.service = service; }

    @GetMapping(value = "/admin")
    public ModelAndView viewAdminPage(
            ModelAndView modelAndView,
            @ModelAttribute("user") User user) {
        List<User> users = service.getAllUsers();
        modelAndView.setViewName("adminMainPage");
        modelAndView.addObject("list", users);
        return modelAndView;
    }

    @PostMapping(value = "/admin")
    public ModelAndView adminCreateUser(
            @ModelAttribute("user") User user,
            ModelAndView modelAndView) {

        if (!user.getUsername().isEmpty()&& !user.getPassword().isEmpty()) { service.saveUser(user); }
        modelAndView.setViewName("redirect:/admin");
        return modelAndView;
    }

    @PostMapping(value = "/admin/delete")
    public ModelAndView adminDeleteUser(
            @RequestParam("id") Long id,
            ModelAndView modelAndView) {

        if (id != null) { service.deleteUserById(id); }
        modelAndView.setViewName("redirect:/admin");
        return modelAndView;
    }

    @PostMapping(value = "/admin/edit")
    public ModelAndView adminEditUser(
            @ModelAttribute("user") User user,
            @RequestParam("password") String newUserPassword,
            ModelAndView modelAndView) {

            user.setPassword(newUserPassword);
            service.editUser(user);
            modelAndView.setViewName("redirect:/admin");
        return modelAndView;
    }
}
