package com.csside.mail.controller.api;


import com.csside.mail.controller.api.request.LocationCodeRequest
import com.csside.mail.controller.api.request.TourRequest
import com.csside.mail.controller.api.response.TourApiResponse
import com.csside.mail.controller.api.response.body.LocationCodeResponse
import com.csside.mail.service.TourService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

//TODO: https://www.data.go.kr/tcs/dss/selectApiDataDetailView.do?publicDataPk=15101578#/API%20%EB%AA%A9%EB%A1%9D/searchStay
@RestController
class TourApiController(
    val tourService: TourService
) {

    @PostMapping("/api/user-location-tour")
    fun getTourListByUserLocation(tourRequest : TourRequest) :String {
       return tourService.findByUserLocation(tourRequest);
    }

    @PostMapping("/api/location-code")
    fun getLocationCode(@RequestBody locationCodeRequest : LocationCodeRequest) :TourApiResponse<LocationCodeResponse>{
        return tourService.findAllLocationCode(locationCodeRequest);
    }

    @GetMapping("/api/alive")
    fun test() = "ok"


}
