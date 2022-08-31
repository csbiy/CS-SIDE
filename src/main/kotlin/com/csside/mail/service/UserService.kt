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

    private val logger = LoggerFactory.getLogger(this::class.java)
    override fun loadUserByUsername(username: String): UserDetails {
        val foundUser = userRepository.findByEmail(username) ?. getUserDetail()  ?: throw UserNotExistException()
        logger.info("login succeed -- user email :{},user authority : {}",foundUser.username,foundUser.authorities)
        return foundUser
    }

    fun saveUser(user:AppUser) :AppUser{
        user.password = pwEncoder.encode(user.password)
        return userRepository.save(user)
    }
    fun isAlreadyExistEmail(email:String) = userRepository.findByEmail(email) != null
}