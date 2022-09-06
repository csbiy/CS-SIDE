package com.csside.mail.service

import com.csside.mail.entity.user.AppUser
import com.csside.mail.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class UserService(@Autowired private val userRepository :UserRepository,
                  @Autowired private val pwEncoder : PasswordEncoder) {


    fun saveUser(user:AppUser) :AppUser{
        user.pw = pwEncoder.encode(user.pw)
        return userRepository.save(user)
    }

    fun updateUser(user: AppUser){
        userRepository.findByEmail(user.email)?.let { updateUser(it, user) } ?: userRepository.save(user)
    }

    private fun updateUser(from: AppUser, to: AppUser) {
        from.appUserName = to.appUserName
        from.role = to.role
        from.pw = to.pw
    }

    fun findByEmail(email:String) = userRepository.findByEmail(email)
}