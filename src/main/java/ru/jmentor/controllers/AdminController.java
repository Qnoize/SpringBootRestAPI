package ru.jmentor.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    @RequestMapping("/admin")
    public String viewAdminPage() { return "adminMainPage"; }
}
