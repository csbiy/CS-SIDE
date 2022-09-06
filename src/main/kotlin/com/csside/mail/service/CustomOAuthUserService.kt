package com.csside.mail.service

import com.csside.mail.component.OAuth2UserAdapter
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class CustomOAuthUserService(val userService: UserService,val oAuth2UserHandler: OAuth2UserAdapter) : OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final val delegate = DefaultOAuth2UserService()
    override fun loadUser(req: OAuth2UserRequest): OAuth2User {
        val loadUser = delegate.loadUser(req)
        val appUser = oAuth2UserHandler.toAppUser(oauth2User = loadUser , req = req);
        userService.updateUser(appUser)
        return loadUser;
    }
}