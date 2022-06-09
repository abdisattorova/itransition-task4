package com.itransition.usermanagementsystem.controller;

import com.itransition.usermanagementsystem.dto.UserRegistrationDto;
import com.itransition.usermanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("/registration")
    public String registrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "registration";
    }


    @PostMapping("/registration")
    public String registerUser(
            @Valid
            @ModelAttribute("user")
                    UserRegistrationDto registrationDto,
            BindingResult result) {
        if (result.hasErrors()) {
            return "registration";
        }

        if (userService.existsByEmail(registrationDto.getEmail())) {
            result.addError(new
                    FieldError("user",
                    "email",
                    "Email is already taken!"));
            return "registration";
        }

        userService.save(registrationDto);
        return "redirect:/registration?success";
    }


    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id, HttpServletRequest request) {
        userService.deleteUser(id, request);
        return "redirect:/";
    }


    @GetMapping("/block/{id}")
    public String blockUser(@PathVariable Long id, HttpServletRequest request) {
        userService.blockUser(id, request);
        return "redirect:/";
    }


    @GetMapping("/deleteAll")
    public String deleteAll() {
        userService.deleteAll();
        return "redirect:/registration";
    }


    @GetMapping("/blockAll")
    public String blockAll(HttpServletRequest request) {
        userService.blockAll(request);
        return "redirect:/registration";
    }


    @GetMapping("/unblockAll")
    public String unblockAll() {
        userService.unblockAll();
        return "redirect:/";
    }


}
