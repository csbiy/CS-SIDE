package com.csside.mail.entity

import com.csside.mail.enumeration.DelFlag
import com.csside.mail.enumeration.DelFlagConverter
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import javax.persistence.*

@MappedSuperclass
abstract class BaseEntity {
    @CreatedDate
    @Column(name = "create_date" , nullable = false)
    open val createdAt: LocalDateTime = LocalDateTime.now()

    @Convert(converter = DelFlagConverter::class)
    @Column(nullable = false)
    protected val delFlag: DelFlag = DelFlag.N

    @CreatedBy
    @Column(nullable = false)
    protected val createdBy: String =  "admin"
}

