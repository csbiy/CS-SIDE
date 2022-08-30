package com.csside.mail.controller

import com.csside.mail.model.RegisterForm
import com.csside.mail.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

@Controller
class HomeController(@Autowired val userRepository: UserRepository) {

    final val logger = LoggerFactory.getLogger(this::class.java)
    @GetMapping("/home")
    fun home() = "home";
    @GetMapping("/login")
    fun login(model : Model) :String {
        model.addAttribute("registerForm", RegisterForm())
        return "login"
    }
    @PostMapping("/register")
    fun registerUser(@ModelAttribute registerForm: RegisterForm ):String{
        // TODO registerForm validation
        userRepository.save(registerForm.toUser());
        return "home"
    }
}