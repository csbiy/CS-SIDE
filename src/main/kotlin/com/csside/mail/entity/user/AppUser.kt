package com.csside.mail.entity.user

import com.csside.mail.entity.BaseEntity
import com.csside.mail.enumeration.UserRole
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.oauth2.core.user.OAuth2User
import javax.persistence.*

@Entity
class AppUser(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val userKey :Int = 0,
    var pw :String,
    val email:String,
    val appUserName:String,
    @Enumerated(EnumType.STRING)
    val role : UserRole
) : BaseEntity() , UserDetails , OAuth2User {
    override fun getName() = email
    override fun getAttributes(): MutableMap<String, Any> {
        return mutableMapOf()
    }
    override fun getAuthorities() = listOf(SimpleGrantedAuthority("ROLE_$role"))
    override fun getPassword() = pw
    override fun getUsername() = email
    override fun isAccountNonExpired()  = true
    override fun isAccountNonLocked() = true
    override fun isCredentialsNonExpired() = true
    override fun isEnabled() = true

}


