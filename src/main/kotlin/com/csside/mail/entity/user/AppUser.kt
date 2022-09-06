package com.csside.mail.entity.user

import com.csside.mail.entity.BaseEntity
import com.csside.mail.enumeration.UserRole
import com.csside.mail.enumeration.UserType
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
open class AppUser(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val userKey :Int = 0,
    var pw :String,
    var email:String,
    var appUserName:String,
    @Enumerated(EnumType.STRING)
    var role : UserRole = UserRole.USER,
    @Enumerated(EnumType.STRING)
    var userType : UserType = UserType.NORMAL
) : BaseEntity() {

    fun toUserDetails(): UserDetails = User(appUserName,pw,listOf(SimpleGrantedAuthority("ROLE_$role")))


}



