package ru.jmentor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.jmentor.model.User;
import ru.jmentor.service.UserService;

@Controller
public class RegisterController {

    private UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @GetMapping(value = "/register")
    public ModelAndView viewRegisterPage(ModelAndView modelAndView) {
        modelAndView.setViewName("registerUser");
        return modelAndView;
    }

    @PostMapping(value = "/register")
    public ModelAndView registerNewUser(
            @ModelAttribute("user") User user,
            ModelAndView modelAndView) {

        if (user.getUsername().isEmpty() | user.getPassword().isEmpty()) {
            modelAndView.addObject("error", "Error - Empty user Login or Password");
            modelAndView.setViewName("registerUser");
        } else {
            service.register(user);
            modelAndView.setViewName("redirect:/");
        }
        return modelAndView;
    }
}
