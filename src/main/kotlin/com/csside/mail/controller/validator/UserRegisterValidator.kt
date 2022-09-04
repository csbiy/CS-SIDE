package com.csside.mail.controller.validator

import com.csside.mail.model.RegisterForm
import com.csside.mail.service.UserService
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator

@Component
class UserRegisterValidator(val userService: UserService):Validator {

    final val invalidPwRegex = Regex("[0-9]*")
    final val validEmailRegex = Regex("^(.+)@(.+)$")
    final val PASSWORD_MIN_LENGTH = 8
    final val USERNAME_MIN_LENGTH = 2

    override fun supports(clazz: Class<*>) = RegisterForm::class.java.isAssignableFrom(clazz)

    override fun validate(target: Any, errors: Errors) {
        val registerForm = target as RegisterForm
        isRequiredFieldEmptry(registerForm,errors)
        if (errors.hasErrors()){
            return
        }
        if (registerForm.name.length < USERNAME_MIN_LENGTH){
            errors.rejectValue("name","name.invalid")
        }
        if (registerForm.password.length < PASSWORD_MIN_LENGTH || invalidPwRegex.matches(registerForm.password) ){
            errors.rejectValue("password","password.invalid")
        }
        if (!registerForm.password.equals(registerForm.repeatPassword)){
            errors.rejectValue("password","password.notMatch")
        }
        if (!validEmailRegex.matches(registerForm.email)){
            errors.rejectValue("email","email.invalid")
            return
        }
        if(userService.isAlreadyExistEmail(registerForm.email)) {
            errors.rejectValue("email","email.exist")
        }

    }

    fun isRequiredFieldEmptry(registerForm : RegisterForm , errors: Errors){
        if(registerForm.name.isNullOrBlank()){
            errors.rejectValue("name","name.require")
        }
        if (registerForm.email.isNullOrBlank()){
            errors.rejectValue("email","email.require")
        }
        if (registerForm.password.isNullOrBlank() || registerForm.repeatPassword.isNullOrBlank()) {
            errors.rejectValue("password", "password.require")
        }
    }
}