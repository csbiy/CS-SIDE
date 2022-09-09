package com.csside.mail.security.service

import com.csside.mail.security.exception.UserNotExistException
import com.csside.mail.service.UserService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(val userService: UserService) :UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails = userService.findByEmail(username)?.toUserDetails() ?: throw UserNotExistException()

}