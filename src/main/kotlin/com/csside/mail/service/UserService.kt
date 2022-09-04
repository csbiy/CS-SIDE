package com.csside.mail.service

import com.csside.mail.entity.user.AppUser
import com.csside.mail.exception.UserNotExistException
import com.csside.mail.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class UserService(@Autowired private val userRepository :UserRepository,
                  @Autowired private val pwEncoder : PasswordEncoder) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails =userRepository.findByEmail(username) ?: throw UserNotExistException()
    fun saveUser(user:AppUser) :AppUser{
        user.pw = pwEncoder.encode(user.pw)
        return userRepository.save(user)
    }
    fun isAlreadyExistEmail(email:String) = userRepository.findByEmail(email) != null
}