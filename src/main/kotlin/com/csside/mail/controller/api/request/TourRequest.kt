package com.csside.mail.controller.api.request

import com.fasterxml.jackson.annotation.JsonProperty

data class TourRequest(var numOfRows :Int ,
                       var pageNo  :Int ,
                       @JsonProperty("MobileOS")
                       var mobileOs :String,
                       @JsonProperty("MobileApp")
                       var mobileApp :String,
                        ) {
}