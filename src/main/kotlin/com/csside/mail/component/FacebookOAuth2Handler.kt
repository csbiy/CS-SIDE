package com.csside.mail.component

import com.csside.mail.entity.user.AppUser
import com.csside.mail.enumeration.UserType
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Component

@Component
class FacebookOAuth2Handler :OAuth2UserHandler {
    override val clientName: String
        get() = "Facebook"

    override fun toAppUser(oauthUser: OAuth2User): AppUser {
        val pw = oauthUser.attributes.get("id") as String
        val name = oauthUser.attributes.get("name") as String;
        val email = oauthUser.attributes.get("email") as String;
        return AppUser(pw = "{social}${pw}", appUserName = name , email = email,  userType = UserType.OAUTH2)
    }
}