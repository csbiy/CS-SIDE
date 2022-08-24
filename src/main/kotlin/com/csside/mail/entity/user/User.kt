package com.csside.mail.entity.user

import com.csside.mail.entity.BaseEntity
import com.csside.mail.enumeration.UserRole
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.stream.Collectors
import javax.persistence.*

@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val userKey :Int = 0,
    val userId :String,
    var password:String,
    val email:String,
    val name:String,
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    val roles : Set<UserRole>
) : BaseEntity(){

    fun getUserDetail(): org.springframework.security.core.userdetails.User {
       return org.springframework.security.core.userdetails.User(email, password, roles.stream().map {
            SimpleGrantedAuthority("ROLE_$it")
        }.collect(Collectors.toSet()))
    }
}


