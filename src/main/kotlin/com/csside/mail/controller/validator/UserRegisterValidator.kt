package com.csside.mail.controller.validator

import com.csside.mail.model.RegisterForm
import com.csside.mail.service.UserService
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator

@Component
class UserRegisterValidator(val userService: UserService):Validator {

    override fun supports(clazz: Class<*>) = RegisterForm::class.java.isAssignableFrom(clazz)

    override fun validate(target: Any, errors: Errors) {
        val registerForm = target as RegisterForm
        if(userService.isAlreadyExistEmail(registerForm.email)) {
            errors.rejectValue("email","email.exist")
        }

    }
}