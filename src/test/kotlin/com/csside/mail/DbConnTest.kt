package com.csside.mail

import com.csside.mail.entity.user.AppUser
import com.csside.mail.enumeration.UserRole
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.persistence.EntityManagerFactory

@SpringBootTest
class DbConnTest {

    @Autowired
    lateinit var enf : EntityManagerFactory
    @Test
    fun testDbConnection(){
        Assertions.assertThat(enf).isNotNull()
    }

    @Test
    fun testShouldCreateUser(){

        val em = enf.createEntityManager();
        em.transaction.begin()
        val user = AppUser(pw = "kim13032695", email = "katd6@naver.com", appUserName = "cskim96", role = UserRole.ADMIN)
        Assertions.assertThat(user.createdAt).isNotNull();
        em.persist(user)
        em.transaction.rollback()
        Assertions.assertThat(user.userKey).isNotEqualTo(0);

    }
}