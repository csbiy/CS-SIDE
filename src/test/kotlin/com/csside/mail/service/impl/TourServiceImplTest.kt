package com.csside.mail.service.impl

import com.csside.mail.service.TourService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class TourServiceImplTest{

    @Autowired
    lateinit var tourService: TourService

    @Test
    fun `should retrieve locationMap`(){
        //TODO locationCode 긁어오는 API client 코드 테스트
    }
}

