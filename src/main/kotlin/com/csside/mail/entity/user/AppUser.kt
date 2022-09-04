package com.csside.mail.entity.user

import com.csside.mail.entity.BaseEntity
import com.csside.mail.enumeration.UserRole
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
class AppUser(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val userKey :Int = 0,
    var pw :String,
    val email:String,
    val name:String,
    @Enumerated(EnumType.STRING)
    val role : UserRole
) : BaseEntity() , UserDetails{

    override fun getAuthorities() = listOf(SimpleGrantedAuthority("ROLE_$role"))
    override fun getPassword() = pw
    override fun getUsername() = email
    override fun isAccountNonExpired()  = true
    override fun isAccountNonLocked() = true
    override fun isCredentialsNonExpired() = true
    override fun isEnabled() = true

}


