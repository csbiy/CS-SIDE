package com.csside.mail.security

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootTest
internal class BcryptEncodingTest{

    @Autowired
    lateinit var pwEncoder :PasswordEncoder
    @Test
    fun `Bcrypt encoding and decoding Test`(){
        val userPw = "kim13032695";
        val encodedPw = pwEncoder.encode(userPw);
        Assertions.assertThat(pwEncoder.matches(encodedPw,userPw)).isTrue()
    }
}