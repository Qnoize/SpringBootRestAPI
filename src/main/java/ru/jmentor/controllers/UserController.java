package ru.jmentor.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;

@RestController
public class UserController {

    @GetMapping(value = "/userHome")
    public ModelAndView viewUserHomePage(
            HttpSession session,
            ModelAndView modelAndView) {

        modelAndView.addObject("name", session.getAttribute("name"));
        modelAndView.addObject("role", session.getAttribute("role"));
        modelAndView.setViewName("userHome");
        return modelAndView;
    }
    @PostMapping(value = "/userHome")
    public ModelAndView userHomePage(ModelAndView modelAndView) {

        modelAndView.setViewName("loginUser");
        return modelAndView;
    }
}
