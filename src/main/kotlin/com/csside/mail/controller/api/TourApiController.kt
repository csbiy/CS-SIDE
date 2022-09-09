package com.csside.mail.controller.api;

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

//TODO: https://www.data.go.kr/tcs/dss/selectApiDataDetailView.do?publicDataPk=15101578#/API%20%EB%AA%A9%EB%A1%9D/searchStay
@RestController
class TourApiController {
    @PostMapping("/locationBasedTour")
    fun getTourListByUserLocation(tourRequest : TourRequest) = {

    }
}
