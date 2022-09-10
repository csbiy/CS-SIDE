package com.csside.mail.config

import com.csside.mail.security.oauth.KakaoOAuth2Handler
import com.csside.mail.security.oauth.NaverOAuth2Handler
import com.csside.mail.security.oauth.OAuth2UserAdapter
import com.csside.mail.security.oauth.OAuth2UserHandler
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.http.MediaType
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.reactive.function.client.WebClient

@Configuration
@EnableScheduling
class WebConfig(val env:Environment) {

    @Bean
    fun pwEncoder() : PasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()

    @Bean
    fun webClient() :WebClient{
        return WebClient.builder()
            .baseUrl(env.getProperty("api.tour.endpoint") as String)
            .defaultHeader("accept", MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader("content-Type",MediaType.APPLICATION_JSON_VALUE)
            .build();
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