package com.csside.mail.config

import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@EnableWebSecurity
@Configuration
@Order(0)
class SecurityConfig :WebSecurityConfigurerAdapter(){

    override fun configure(http: HttpSecurity) {
        http
            .authorizeRequests().antMatchers("/login").permitAll()
            .antMatchers("/css/**","/js/**").permitAll() // css , js 에 대한 접근은 허용 설정
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/login_proc")
    }
}