package com.csside.mail.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder

@EnableWebSecurity
@Configuration
class Config(@Autowired private val userDetailService : UserDetailsService,
             @Autowired private val pwEncoder : PasswordEncoder
    ) :WebSecurityConfigurerAdapter(){

    val LOGIN_SUCCESS_URL ="/home"
    override fun configure(http: HttpSecurity) {
        http.anonymous()
            .and()
            .formLogin()
            .successForwardUrl(LOGIN_SUCCESS_URL)
            .and()
            .authorizeRequests()
            .anyRequest()
            .authenticated()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth
            .userDetailsService(userDetailService)
            .passwordEncoder(pwEncoder)
    }


}