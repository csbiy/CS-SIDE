package com.csside.mail.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@EnableWebSecurity
@Configuration
class SecurityConfig(@Autowired private val userDetailService : UserDetailsService,
                     @Autowired private val pwEncoder : PasswordEncoder) :WebSecurityConfigurerAdapter(){

    final val LOGIN_SUCCESS_URL ="/home"

    override fun configure(http: HttpSecurity) {
        http.csrf().disable() /*FORM LOGIN 에 csrf parameter 추가 */
        http
            .authorizeRequests().antMatchers("/login","/login_proc","/register").permitAll()
            .antMatchers("/css/**","/js/**").permitAll() // css , js 에 대한 접근은 허용 설정
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/login_proc")
            .usernameParameter("email")
            .successHandler({ req,res,auth-> res.sendRedirect("/home") })
            .failureForwardUrl("/login")

    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        println("auth config exeucted")
        auth
            .userDetailsService(userDetailService)
            .passwordEncoder(pwEncoder)
    }
}

