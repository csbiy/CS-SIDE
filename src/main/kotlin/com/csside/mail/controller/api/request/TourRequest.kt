package com.csside.mail.controller.api.request

import com.csside.mail.enumeration.TourTypeId
import com.fasterxml.jackson.annotation.JsonProperty

class TourRequest(numOfRows: Int, pageNo: Int) :
    BaseRequest(numOfRows, pageNo) {

    var listYN :String = "Y"
    var arrange : String = "C"
    lateinit var contentTypeId : TourTypeId
    lateinit var areaCode : String
    lateinit var sigunguCode : String
    lateinit var cat1 : String
    lateinit var cat2:String
    lateinit var cat3:String
    lateinit var modifiedTime:String
}