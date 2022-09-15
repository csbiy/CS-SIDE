package com.csside.mail.service.impl

import com.csside.mail.controller.api.request.LocationCodeRequest
import com.csside.mail.model.LocationCode
import com.csside.mail.service.TourService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.stream.Collectors

@SpringBootTest
internal class TourServiceImplTest{

    @Autowired
    lateinit var tourService: TourService

    @Test
    fun `should retrieve locationMap correctly`(){
        val map = tourService.getLocationMap();
        val seoul = tourService.findLocationCode(LocationCodeRequest.findAllByCode(""))
            .block()!!.response.body.items.item.stream().filter { it.name.equals("서울") }.findFirst().get()

        val result = map.get(LocationCode(locationCode = seoul.code , locationName = seoul.name))!!.stream().map { it.locationName }.collect(
            Collectors.toList())
        Assertions.assertThat(result).contains("강남구","강동구","강북구","강서구","관악구","광진구","구로구","금천구","노원구","도봉구");
    }
}

