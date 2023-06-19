package com.example.crudapp.controller;


import com.example.crudapp.entity.User;
import com.example.crudapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/")
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping("signup")
    public String signup(@Valid User user, BindingResult result, Model model){
        model.addAttribute("user", new User());
        return "view/signup";
    }

    @PostMapping("signup")
    public  String register(@Valid User user, BindingResult result, Model model){
        String err = userService.validateEmail(user);
        if (!err.isEmpty()) {
            ObjectError error = new ObjectError("globalError", err);
            result.addError(error);
        }
        if (result.hasErrors()) {
            return "view/signup";
        }
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        System.out.println("HI =>" + user.getRole().toString());
        userService.signUp(user);
        return "view/login";
    }

    @GetMapping("login")
    public String loginPage(@Valid User user, BindingResult result, Model model){
        model.addAttribute("user", new User());
        return "view/login";
    }

    @PostMapping("login")
    public  String login(@Valid User user, BindingResult result, Model model){
        String err = userService.validateEmail(user);
        if (!err.isEmpty()) {
            ObjectError error = new ObjectError("globalError", err);
            result.addError(error);
        }
        if (result.hasErrors()) {
            return "view/login";
        }
        return "view/home";
    }
}
