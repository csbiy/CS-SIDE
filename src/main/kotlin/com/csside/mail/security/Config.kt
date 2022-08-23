package com.csside.mail.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@EnableWebSecurity
@Configuration
class Config :WebSecurityConfigurerAdapter(){

    @Bean
    fun pwEncoder() :PasswordEncoder = BCryptPasswordEncoder()

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .antMatchers("/home")
            .access("hasRole('USER')")
            .antMatchers("/*")
            .permitAll()
            .and()
            .httpBasic()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication()
            .withUser("admin")
            .password(pwEncoder().encode("1234"))
            .authorities("USER");
    }


}