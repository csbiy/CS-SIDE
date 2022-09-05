package com.csside.mail.service

import com.csside.mail.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class OAuthUserService(val userRepository: UserRepository) : OAuth2UserService<OAuth2UserRequest,OAuth2User> {

    private final val delegate = DefaultOAuth2UserService()
    private final val logger = LoggerFactory.getLogger(this::class.java)

    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val loadUser = delegate.loadUser(userRequest)
        // TODO user를 oauth2User , UserDetails에 직접적으로 상속하지말고, 추상화해서 사용하자 --> adapterPattern

        return loadUser;
    }

}
