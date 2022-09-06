package com.csside.mail.service

import com.csside.mail.entity.user.AppUser
import com.csside.mail.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class UserService(@Autowired private val userRepository :UserRepository,
                  @Autowired private val pwEncoder : PasswordEncoder) {

    val logger: Logger = LoggerFactory.getLogger(this::class.java)

    fun saveUser(user:AppUser) :AppUser{
        user.pw = pwEncoder.encode(user.pw)
        return userRepository.save(user)
    }

    fun updateUser(user: AppUser){
        userRepository.findByEmailAndUserType(user.email , user.userType )?.let {
            logger.info("user[{}] already exist , start updating user" ,user.email)
            updateUser(it, user)
        } ?: userRepository.save(user)
    }


    private fun updateUser(from: AppUser, to: AppUser) {
        from.appUserName = to.appUserName
        from.role = to.role
        from.pw = to.pw
    }

    fun findByEmail(email:String) = userRepository.findByEmail(email)


}