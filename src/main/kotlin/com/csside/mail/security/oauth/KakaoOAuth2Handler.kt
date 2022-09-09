package com.csside.mail.security.oauth

import com.csside.mail.entity.user.AppUser
import com.csside.mail.enumeration.UserType
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Component

@Component
class KakaoOAuth2Handler(val objectMapper: ObjectMapper): OAuth2UserHandler {
    override val clientName: String
        get() = "kakao"

    override fun toAppUser(oauthUser: OAuth2User): AppUser {
        val res = this.objectMapper.convertValue(oauthUser.attributes, Response::class.java)
        return AppUser( pw = "{social}${res.id}", email = res.kakaoAccount.email,  appUserName = res.kakaoAccount.profile.nickname, userType = UserType.OAUTH2)
    }

    class Response {
        lateinit var id :String
        @JsonProperty("kakao_account")
        lateinit var kakaoAccount: KaKaoAccount
        class KaKaoAccount{
            lateinit var profile : Profile
            lateinit var email :String
        }
        class Profile {
            lateinit var nickname :String;
        }
    }

}

