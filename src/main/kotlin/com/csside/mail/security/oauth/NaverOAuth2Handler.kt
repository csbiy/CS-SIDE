package com.csside.mail.security.oauth

import com.csside.mail.entity.user.AppUser
import com.csside.mail.enumeration.UserType
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Component

@Component
class NaverOAuth2Handler(val objcetMapper: ObjectMapper): OAuth2UserHandler {

    override val clientName: String
        get() = "naver"
    override fun toAppUser(oauthUser: OAuth2User): AppUser {
        oauthUser.attributes.get("response")?.let {
            val res = objcetMapper.convertValue(it, Response::class.java)
            return AppUser(pw = "{social}${res.id}" , email =  res.email, appUserName = res.name, userType = UserType.OAUTH2)
        } ?: throw IllegalArgumentException("naver oauth2 response does not contain response field")
    }

    class Response {
        lateinit var id :String
        lateinit var email:String
        lateinit var name : String
    }
}

