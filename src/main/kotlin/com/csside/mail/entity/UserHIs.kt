package com.csside.mail.entity

import com.csside.mail.enumeration.DelFlag
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class UserHis(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val userKey :Int,
    @Id
    val input_time:LocalDateTime,
    @Column
    val userId :String,
    @Column
    val password:String,
    @Column
    val email:String,
    @Column
    val name:String
){

}