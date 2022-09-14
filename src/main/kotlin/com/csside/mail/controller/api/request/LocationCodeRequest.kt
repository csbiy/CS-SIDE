package com.csside.mail.controller.api.request

class LocationCodeRequest(numOfRows:Int ,pageNo :Int ,var areaCode:String) :
    BaseRequest(numOfRows, pageNo) {
    companion object {

        fun findAllByCode(areaCode:String) = LocationCodeRequest(1000,0,areaCode)

    }
}