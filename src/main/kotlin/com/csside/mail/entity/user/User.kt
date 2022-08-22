package com.csside.mail.entity.user

import com.csside.mail.entity.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val userKey :Int = 0,
    @Column
    val userId :String,
    @Column
    val password:String,
    @Column
    val email:String,
    @Column
    val name:String
) : BaseEntity()
