package com.csside.mail.entity

import com.csside.mail.enumeration.DelFlag
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
internal class UserTest{


    @Test
    fun saveUser(){
      val user = User(name = "tester", createdAt = LocalDateTime.now() , delFlag = DelFlag.N , createdBy = "admin", id = "testId");
    }
}