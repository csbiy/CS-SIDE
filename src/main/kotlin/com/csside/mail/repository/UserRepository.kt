package com.csside.mail.repository

import com.csside.mail.entity.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository :JpaRepository<User,Int>{

    fun findByEmail(email:String) : User?
}