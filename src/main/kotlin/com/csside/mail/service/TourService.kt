package com.csside.mail.service

import com.csside.mail.controller.api.request.LocationCodeRequest
import com.csside.mail.controller.api.request.TourRequest
import com.csside.mail.controller.api.response.TourApiResponse
import com.csside.mail.controller.api.response.body.LocationCodeResponse

interface TourService {

    fun findByUserLocation(tourRequest: TourRequest) :String;

    fun findAllLocationCode(locationCodeRequest: LocationCodeRequest): TourApiResponse<LocationCodeResponse>
}