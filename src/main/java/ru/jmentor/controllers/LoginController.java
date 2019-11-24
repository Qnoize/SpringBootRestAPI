package ru.jmentor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.jmentor.form.LoginForm;
import ru.jmentor.service.LoginService;
import ru.jmentor.transfer.TokenDto;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping(value = "/")
    public ModelAndView viewLoginPage(
            Authentication authentication,
            ModelAndView modelAndView,
            HttpServletRequest request) {

        if(authentication != null){ modelAndView.setViewName("userHome"); }

        if(request.getParameterMap().containsKey("error")){
            modelAndView.addObject("error", "Error - Wrong user Login or Password");
        }
        modelAndView.setViewName("loginUser");
        return modelAndView;
    }

    @PostMapping("/")
    public ResponseEntity<TokenDto> login(@RequestBody LoginForm loginForm){
        return ResponseEntity.ok(loginService.login(loginForm));
    }
}
