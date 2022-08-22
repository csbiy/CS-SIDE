package com.csside.mail.entity.user

import com.csside.mail.entity.BaseEntity
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass

@Entity
@IdClass(UserHisPk::class)
class UserHis(
    @Column
    val userId :String,
    @Column
    val password:String,
    @Column
    val email:String,
    @Column
    val name:String,
    @Id
    val createdAt: LocalDateTime,
    @Id
    val userKey:Int,
)


data class UserHisPk(var userKey:Int,var createdAt: LocalDateTime) :java.io.Serializable

