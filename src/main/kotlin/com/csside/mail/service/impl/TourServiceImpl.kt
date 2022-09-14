package com.csside.mail.service.impl

import com.csside.mail.controller.api.request.LocationCodeRequest
import com.csside.mail.controller.api.request.TourRequest
import com.csside.mail.controller.api.response.TourApiResponse
import com.csside.mail.controller.api.response.body.LocationCodeResponse
import com.csside.mail.model.LocationCode
import com.csside.mail.service.TourService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.core.ParameterizedTypeReference
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.util.stream.Collectors

@Service
class TourServiceImpl(
    val webClient: WebClient,
    val objectMapper: ObjectMapper
):TourService {

    private val locationCodeUrl = "/areaCode"

    final var cachedLocationMap: Map<LocationCode, List<LocationCode>> = createLocationMap()
    private set(value){
        field = value
    }

    override fun getLocationMap(): Map<LocationCode, List<LocationCode>> = cachedLocationMap
    override fun findByUserLocation(req: TourRequest): String {
        TODO("Not yet implemented")
    }

    override fun findLocationCode(req: LocationCodeRequest) :Mono<TourApiResponse<LocationCodeResponse>> {
        val map = objectMapper.convertValue(req, Map::class.java)
        val newMap: Map<String, String> =
            map.entries.stream().collect(Collectors.toMap({ it.key as String }, { it.value.toString() }))
        val multiValueMap = LinkedMultiValueMap<String, String>().apply { setAll(newMap) }
        return webClient.get()
            .uri({
                it.path(locationCodeUrl)
                it.queryParams(multiValueMap)
                it.build()
            })
            .retrieve()
            .bodyToMono(object : ParameterizedTypeReference<TourApiResponse<LocationCodeResponse>>() {});
    }

    @Scheduled(cron = "0 0 0 * * *")
    override fun updateLocationMap() {
        cachedLocationMap =this.createLocationMap()
    }

    private fun createLocationMap() :Map<LocationCode, List<LocationCode>> {
        this.findLocationCode(LocationCodeRequest.findAllByCode("")).block()?.also { city->
            val locationMap: MutableMap<LocationCode, List<LocationCode>> = mutableMapOf()
            unpackResponse(city).stream().forEach{ cityRes->
                val city = LocationCode(locationName = cityRes.name, locationCode = cityRes.code)
                this.findLocationCode(LocationCodeRequest.findAllByCode(cityRes.code)).doOnNext { streetRes ->
                    val street = unpackResponse(streetRes).stream().map{LocationCode(locationName = it.name, locationCode = it.code)}.collect(Collectors.toList())
                    locationMap.put(city,street)
                }.block()
            }
            return locationMap.toMap()
        }
        throw AssertionError()
    }

    private fun unpackResponse(tourApiResponse: TourApiResponse<LocationCodeResponse>) :List<LocationCodeResponse>{
        return tourApiResponse.response.body.items.item
    }
}