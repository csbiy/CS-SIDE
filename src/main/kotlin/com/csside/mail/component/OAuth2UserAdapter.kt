package com.csside.mail.component

import com.csside.mail.entity.user.AppUser
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User

class OAuth2UserAdapter(private val handlers : List<OAuth2UserHandler>){

    fun toAppUser( req: OAuth2UserRequest , oauth2User :OAuth2User) :AppUser{
        return getHandler(req.clientRegistration.clientName).let {
            it.toAppUser(oauth2User)
        }
    }


    fun getHandler(clientName: String): OAuth2UserHandler {
        return this.handlers.stream().filter { it.supports(clientName) }.findFirst()
            .orElseThrow { throw IllegalArgumentException("OAuth2UserHandler not found for clientId :[${clientName}]") }
    }


}