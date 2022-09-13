package com.csside.mail.config

import com.csside.mail.security.oauth.KakaoOAuth2Handler
import com.csside.mail.security.oauth.NaverOAuth2Handler
import com.csside.mail.security.oauth.OAuth2UserAdapter
import com.csside.mail.security.oauth.OAuth2UserHandler
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.http.MediaType
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.DefaultUriBuilderFactory
import reactor.core.publisher.Mono

@Configuration
@EnableScheduling
class WebConfig(val env:Environment) {
    val logger = LoggerFactory.getLogger(this.javaClass)

    @Bean
    fun pwEncoder() : PasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()

    @Bean
    fun webClient() :WebClient{

        val uriBuilderFactory = DefaultUriBuilderFactory("${env.getProperty("api.tour.endpoint")}?serviceKey=${env.getProperty("api.tour.clientKey")}")
        uriBuilderFactory.encodingMode = DefaultUriBuilderFactory.EncodingMode.NONE
        return WebClient.builder()
            .uriBuilderFactory(uriBuilderFactory)
            .defaultHeader("accept", MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader("content-Type",MediaType.APPLICATION_JSON_VALUE)
            .filter(logRequest())
            .build();
    }


    @Bean
    fun logRequest() :ExchangeFilterFunction{
        return ExchangeFilterFunction.ofRequestProcessor {
            logger.info("Request :{} {}", it.method(), it.url())
            Mono.just(it)
        }
    }

    @Bean
    fun objectMapper() :ObjectMapper{
        val objectMapper = ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        objectMapper.registerKotlinModule()
        return objectMapper;
    }

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