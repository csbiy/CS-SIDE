package com.csside.mail.model

import com.csside.mail.entity.user.AppUser
import com.csside.mail.enumeration.UserRole

data class RegisterForm(var name: String = "",
                        var email: String = "",
                        var password: String = "",
                        var repeatPassword: String = ""){
    fun toUser() =AppUser(password= password, email = email, name = name, role = UserRole.USER)
}