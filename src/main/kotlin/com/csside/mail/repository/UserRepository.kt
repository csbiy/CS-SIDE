package com.csside.mail.repository

import com.csside.mail.entity.user.AppUser
import com.csside.mail.enumeration.UserType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository :JpaRepository<AppUser,Int>{

    fun findByEmail(email:String) : AppUser?

    fun findByEmailAndUserType (email:String , userType: UserType) :AppUser?
}