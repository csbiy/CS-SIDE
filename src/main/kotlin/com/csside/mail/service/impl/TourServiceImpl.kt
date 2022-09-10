package com.csside.mail.service.impl

import com.csside.mail.controller.api.request.LocationCodeRequest
import com.csside.mail.controller.api.request.TourRequest
import com.csside.mail.service.TourService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.core.env.Environment
import org.springframework.core.env.get
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

    override fun findAllLocationCode(req: LocationCodeRequest): String {
        val map = objectMapper.convertValue(req,Map::class.java)
        val newMap :Map<String,String> = map.entries.stream().collect(Collectors.toMap({it.key as String},{it.value.toString()}))
        val multiValueMap = LinkedMultiValueMap<String, String>().apply { setAll(newMap) }
        val response = webClient.get()
            .uri({
                it.path(locationCodeUrl)
                it.queryParams(multiValueMap)
                it.queryParam("serviceKey",env.getProperty("api.tour.clientKey"))
                it.build()
            })
            .retrieve()
            .bodyToMono(String::class.java)
            .block();
        println(response)
        TODO("Not yet implemented")
    }
}