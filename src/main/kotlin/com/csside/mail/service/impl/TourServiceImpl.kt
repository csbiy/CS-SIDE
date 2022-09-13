package com.csside.mail.service.impl

import com.csside.mail.controller.api.request.LocationCodeRequest
import com.csside.mail.controller.api.request.TourRequest
import com.csside.mail.controller.api.response.TourApiResponse
import com.csside.mail.controller.api.response.body.LocationCodeResponse
import com.csside.mail.service.TourService
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.core.env.Environment
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.reactive.function.client.WebClient
import java.util.stream.Collectors

@Service
class TourServiceImpl(val webClient: WebClient ,
                      val env :Environment,
                      val objectMapper: ObjectMapper) :TourService {

    private val locationCodeUrl = "/areaCode"

    override fun findByUserLocation(req: TourRequest): String {
        TODO("Not yet implemented")
    }

    override fun findAllLocationCode(req: LocationCodeRequest): TourApiResponse<LocationCodeResponse> {
        val map = objectMapper.convertValue(req,Map::class.java)
        val newMap :Map<String,String> = map.entries.stream().collect(Collectors.toMap({it.key as String},{it.value.toString()}))
        val multiValueMap = LinkedMultiValueMap<String, String>().apply { setAll(newMap) }
        val response :String? = webClient.get()
            .uri({
                it.path(locationCodeUrl)
                it.queryParams(multiValueMap)
                it.build()
            })
            .retrieve()
            .bodyToMono(String::class.java)
            .block();
        response?.let {
            return objectMapper.readValue(it)
       } ?: throw AssertionError()
    }
}