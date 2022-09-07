package com.csside.mail.oauth

import com.csside.mail.entity.user.AppUser
import com.csside.mail.enumeration.UserType
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Component

@Component
class GoogleOAuth2Handler : OAuth2UserHandler {

    override val clientName: String
        get() = "Google"

    override fun toAppUser(oauthUser: OAuth2User): AppUser {
        val name = oauthUser.attributes.get("name") as String
        val email = oauthUser.attributes.get("email") as String
        val socialPw = oauthUser.attributes.get("sub") as String
        return AppUser( pw = "{social}${socialPw}", email = email,  appUserName = name, userType = UserType.OAUTH2)
    }

}
