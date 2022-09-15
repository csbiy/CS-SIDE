package com.csside.mail.service

import com.csside.mail.controller.api.request.LocationCodeRequest
import com.csside.mail.controller.api.request.UserLocationRequest
import com.csside.mail.controller.api.response.TourApiResponse
import com.csside.mail.controller.api.response.body.LocationCodeResponse
import com.csside.mail.model.LocationCode
import reactor.core.publisher.Mono

interface TourService {

    fun findByUserLocation(req: UserLocationRequest) :String;

    fun findLocationCode(req: LocationCodeRequest): Mono<TourApiResponse<LocationCodeResponse>>

    fun getLocationMap() :Map<LocationCode,List<LocationCode>>

    fun updateLocationMap()
}