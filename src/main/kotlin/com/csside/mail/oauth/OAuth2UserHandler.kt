package com.csside.mail.oauth

import com.csside.mail.entity.user.AppUser
import org.springframework.security.oauth2.core.user.OAuth2User

interface OAuth2UserHandler{

    val clientName:String

    fun toAppUser(oauthUser:OAuth2User) :AppUser

    fun supports(clientName: String) = clientName == this.clientName

}