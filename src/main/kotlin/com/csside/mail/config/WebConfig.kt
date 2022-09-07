package com.csside.mail.config

import com.csside.mail.entity.user.AppUser
import com.csside.mail.enumeration.UserRole
import com.csside.mail.oauth.KakaoOAuth2Handler
import com.csside.mail.oauth.NaverOAuth2Handler
import com.csside.mail.oauth.OAuth2UserAdapter
import com.csside.mail.oauth.OAuth2UserHandler
import com.csside.mail.service.UserService
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
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
        return object : ApplicationRunner {
            @Autowired
            lateinit var userService: UserService
            override fun run(args: ApplicationArguments) {
                val testUser = AppUser(pw = "1234", email = "test@naver.com" , role =
                UserRole.USER, appUserName = "tester")
                userService.saveUser(testUser)
            }
        }
    }

    @Bean
    fun objectMapper(): ObjectMapper = ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false)

    @Bean
    fun oauth2UserHandler(
        googleOAuth2Handler: OAuth2UserHandler,
        facebookOAuth2Handler: OAuth2UserHandler,
        kakaoOAuth2Handler: KakaoOAuth2Handler,
        naverOAuth2Handler: NaverOAuth2Handler
    ): OAuth2UserAdapter {
        val handlers = listOf(googleOAuth2Handler, facebookOAuth2Handler , kakaoOAuth2Handler , naverOAuth2Handler);
        return OAuth2UserAdapter(handlers)
    }


}