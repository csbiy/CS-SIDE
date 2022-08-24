package com.csside.mail.config

import com.csside.mail.entity.user.User
import com.csside.mail.enumeration.UserRole
import com.csside.mail.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class WebConfig {

    @Bean
    fun pwEncoder() : PasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()

    @Bean
    fun applicationRunner() : ApplicationRunner {

        println("application runner executed")
        return object : ApplicationRunner {
            @Autowired
            lateinit var userService: UserService
            override fun run(args: ApplicationArguments) {
                val testUser = User(userId = "test@naver.com" , password = "1234", email = "test@naver.com" , roles = mutableSetOf(
                    UserRole.USER), name = "tester")
                userService.saveUser(testUser)
            }
        }
    }
}