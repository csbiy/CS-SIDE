package com.csside.mail.service

import com.csside.mail.controller.api.request.LocationCodeRequest
import com.csside.mail.controller.api.request.TourRequest

interface TourService {

    fun findByUserLocation(tourRequest: TourRequest) :String;

    fun findAllLocationCode(locationCodeRequest: LocationCodeRequest):String
}