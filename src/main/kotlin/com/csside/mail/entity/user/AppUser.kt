package com.csside.mail.entity.user

import com.csside.mail.entity.BaseEntity
import com.csside.mail.enumeration.UserRole
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import javax.persistence.*

@Entity
class AppUser(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val userKey :Int = 0,
    var password:String,
    val email:String,
    val name:String,
    @Enumerated(EnumType.STRING)
    val role : UserRole
) : BaseEntity(){

    fun getUserDetail(): User {
       return User(email, password, listOf(SimpleGrantedAuthority("ROLE_$role")));
    }
}


