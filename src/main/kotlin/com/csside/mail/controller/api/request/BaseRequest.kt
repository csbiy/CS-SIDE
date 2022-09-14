package com.csside.mail.controller.api.request

import com.fasterxml.jackson.annotation.JsonProperty

abstract class BaseRequest(var numOfRows:Int = 10,
                           var pageNo :Int = 0){

    @JsonProperty("MobileOS")
    var mobileOs :String = "CS-SIDE"
    @JsonProperty("MobileApp")
    var mobileApp :String = "ETC"
    var _type :String = "json"

}