package ru.jmentor.front.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.jmentor.front.dto.UserDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @GetMapping(value = "/userHome")
    public ModelAndView viewUserHomePage(
            HttpSession session,
            ModelAndView modelAndView){
        UserDto userDto = (UserDto) session.getAttribute("user");

        modelAndView.addObject("name", userDto.getFirstName());
        modelAndView.addObject("role", userDto.getUserRole());
        modelAndView.addObject("token", userDto.getToken());
        modelAndView.setViewName("userHome");
        return modelAndView;
    }
}
