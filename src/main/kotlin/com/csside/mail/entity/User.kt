package com.csside.mail.entity

import com.csside.mail.entity.converter.DelFlagConverter
import com.csside.mail.enumeration.DelFlag
import java.time.LocalDateTime
import javax.persistence.AttributeConverter
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Converter
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class User (
    @Column
    val name:String,
    @Column
    val id:String,
    @Column
    val createdAt:LocalDateTime = LocalDateTime.now(),
    @Convert(converter = DelFlagConverter::class)
    @Column
    val delFlag:DelFlag = DelFlag.N,
    @Column
    val createdBy:String = "admin",
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userKey: Long? = null
)

