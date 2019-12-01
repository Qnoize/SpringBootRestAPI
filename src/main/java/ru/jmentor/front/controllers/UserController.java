package ru.jmentor.front.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping(value = "/userHome")
    public String viewUserHomePage(){ return "userHome"; }
}
