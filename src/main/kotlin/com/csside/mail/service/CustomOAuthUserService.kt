package com.csside.mail.service

import com.csside.mail.entity.user.AppUser
import com.csside.mail.enumeration.UserRole
import com.csside.mail.enumeration.UserType
import org.slf4j.LoggerFactory
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class CustomOAuthUserService(val userService: UserService) : OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final val delegate = DefaultOAuth2UserService()
    private final val logger = LoggerFactory.getLogger(this::class.java)

    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val loadUser = delegate.loadUser(userRequest)
        val appUser = convertAppUser(loadUser)
        userService.updateUser(appUser)
        return loadUser;
    }

    private fun convertAppUser(loadUser: OAuth2User): AppUser {
        val name = loadUser.attributes.getOrDefault("name", "") as String
        val email = loadUser.attributes.getOrDefault("email", "") as String
        val socialPw = loadUser.attributes.getOrDefault("sub", "") as String
        val appUser = AppUser(
            pw = "{social}${socialPw}",
            email = email,
            appUserName = name,
            userType = UserType.OAUTH2
        )
        return appUser
    }

}