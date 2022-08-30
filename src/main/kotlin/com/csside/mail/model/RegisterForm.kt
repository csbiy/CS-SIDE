package com.csside.mail.model

import com.csside.mail.entity.user.AppUser

data class RegisterForm(var name: String? = null,
                        var email: String? = null,
                        var password: String? = null,
                        var repeatPassword: String? = null){

    fun toUser() :AppUser {
    //TODO
    }
}