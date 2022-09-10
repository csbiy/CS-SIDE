package com.csside.mail.controller.api.request

class LocationCodeRequest(numOfRows:Int ,pageNo :Int ,var areaCode:String?) :
    BaseRequest(numOfRows, pageNo) {

    constructor() : this(10,0,null)
}