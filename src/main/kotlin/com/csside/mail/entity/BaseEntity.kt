package com.csside.mail.entity

import com.csside.mail.entity.converter.DelFlagConverter
import com.csside.mail.enumeration.DelFlag
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.MappedSuperclass

@MappedSuperclass
open abstract class BaseEntity {
    @CreatedDate
    @Column(name = "create_date")
    protected val createdAt: LocalDateTime = LocalDateTime.now()

    @Convert(converter = DelFlagConverter::class)
    @Column
    protected val delFlag: DelFlag = DelFlag.N

    @CreatedBy
    @Column
    protected val createdBy: String =  "admin"
}