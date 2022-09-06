package com.csside.mail.entity

import com.csside.mail.enumeration.DelFlag
import com.csside.mail.enumeration.DelFlagConverter
import com.csside.mail.enumeration.UserRole
import com.csside.mail.enumeration.UserType
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.time.LocalDateTime
import javax.persistence.*

@MappedSuperclass
@EnableJpaAuditing
abstract class BaseEntity {

    @CreatedDate
    @Column(name = "create_date" , nullable = false)
    open val createdAt: LocalDateTime = LocalDateTime.now()

    @Convert(converter = DelFlagConverter::class)
    @Column(nullable = false)
    protected val delFlag: DelFlag = DelFlag.N

    @CreatedBy
    @Column(nullable = false)
    protected val createdBy: String = UserRole.ADMIN.name
}


