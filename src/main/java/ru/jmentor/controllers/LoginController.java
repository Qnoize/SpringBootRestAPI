package ru.jmentor.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @GetMapping(value = "/")
    public String viewLoginPage() { return "loginUser"; }

}
