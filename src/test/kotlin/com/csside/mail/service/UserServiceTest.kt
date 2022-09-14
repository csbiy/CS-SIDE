package com.csside.mail.service

import com.csside.mail.entity.user.AppUser
import com.csside.mail.enumeration.UserType
import com.csside.mail.repository.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.transaction.Transactional

@SpringBootTest
@Transactional
internal class UserServiceTest {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var userService: UserService
    @Test
    fun `should update user when userType and email Matched`(){

        val oldUser =AppUser(pw = "1234" , email = "cskim@naver.com" , appUserName = "김찬수" , userType = UserType.OAUTH2)
        val newUser =AppUser(pw = "12345678" , email = "cskim@naver.com" , appUserName = "김뉴뉴" , userType = UserType.OAUTH2)
        userRepository.save(oldUser);
        userService.updateUser(newUser)
        userRepository.findByEmail("cskim@naver.com")?.let {
            assertThat(it.pw == newUser.pw)
            assertThat(it.appUserName == newUser.appUserName)
        } ?: fail()
    }
    @Test
    fun `should not update user when userType and email Not Matched`(){

        val oldUser =AppUser(pw = "1234" , email = "cskim@naver.com" , appUserName = "김찬수" , userType = UserType.OAUTH2)
        val newUser =AppUser(pw = "12345678" , email = "cskim@naver.com" , appUserName = "김뉴뉴" , userType = UserType.NORMAL) //
        userRepository.save(oldUser);
        userService.updateUser(newUser)
        userRepository.findByEmail("cskim@naver.com")?.let {
            assertThat(it.pw != newUser.pw)
            assertThat(it.appUserName != newUser.appUserName)
        } ?: fail()
    }
}