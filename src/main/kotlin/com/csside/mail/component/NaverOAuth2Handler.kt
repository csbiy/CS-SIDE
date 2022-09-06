package com.csside.mail.component

import com.csside.mail.entity.user.AppUser
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Component

@Component
class NaverOAuth2Handler :OAuth2UserHandler {

    override val clientName: String
        get() = TODO("Not yet implemented")

    override fun toAppUser(oauthUser: OAuth2User): AppUser {
        TODO("Not yet implemented")
    }
}