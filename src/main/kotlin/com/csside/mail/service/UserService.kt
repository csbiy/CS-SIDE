package com.csside.mail.service

import com.csside.mail.entity.user.User
import com.csside.mail.exception.UserNotExistException
import com.csside.mail.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(@Autowired private val userRepository :UserRepository,
                  @Autowired private val pwEncoder : PasswordEncoder) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
       return userRepository.findByEmail(username)?.getUserDetail()  ?: throw UserNotExistException()
    }

    fun saveUser(user:User) :User{
        user.password = pwEncoder.encode(user.password)
        return userRepository.save(user)
    }
}