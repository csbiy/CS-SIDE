package com.csside.mail.controller

import com.csside.mail.controller.validator.UserRegisterValidator
import com.csside.mail.model.RegisterForm
import com.csside.mail.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

@Controller
class HomeController(val userService: UserService , val validator: UserRegisterValidator) {

    final val logger = LoggerFactory.getLogger(this::class.java)
    @GetMapping("/home")
    fun home() = "home";
    @GetMapping("/login")
    fun login(model : Model) :String {
        model.addAttribute("registerForm", RegisterForm())
        return "login"
    }
    @PostMapping("/register")
    fun registerUser(@ModelAttribute registerForm: RegisterForm , bindingResult: BindingResult  ):String{
        validator.validate(registerForm,bindingResult);
        if (bindingResult.hasErrors()) return "login"
        userService.saveUser(registerForm.toUser());
        return "home"
    }



}