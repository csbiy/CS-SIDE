package com.csside.mail.controller.validator

import com.csside.mail.model.RegisterForm
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.validation.BeanPropertyBindingResult

@SpringBootTest
internal class UserRegisterValidatorTest{

    @Autowired lateinit var validator : UserRegisterValidator

    @Test
    fun `should validate if required field is Empty`(){
        val registerForm = RegisterForm(name = "" , email = "", password = "");
        val errors = BeanPropertyBindingResult(registerForm,"registerForm");
        validator.validate(registerForm,errors)
        assertThat(errors.hasErrors()).isTrue();
        assertThat(errors.getFieldErrors().size).isEqualTo(3);
    }

    @Test
    fun `should validate if email has invalid format`(){
        val strangeEmail = "cscs"
        val registerForm = RegisterForm(name = "test" , email = strangeEmail , password = "test123456", repeatPassword = "test123456")
        val errors = BeanPropertyBindingResult(registerForm,"registerForm");
        validator.validate(registerForm,errors)
        assertThat(errors.hasErrors()).isTrue()
        assertThat(errors.getFieldErrors("email").size).isEqualTo(1)
    }

}
