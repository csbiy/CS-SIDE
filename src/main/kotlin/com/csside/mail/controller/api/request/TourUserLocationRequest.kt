package com.csside.mail.controller.api.request

import com.csside.mail.METER
import com.csside.mail.enumeration.TourTypeId

class TourUserLocationRequest(numOfRows: Int = 12,
                               pageNo: Int = 0,
                               val contentTypeId:String = TourTypeId.ALL.value,
                               userLocationRequest: UserLocationRequest,
                               val radius:Long = METER*50L) : BaseRequest(numOfRows, pageNo) {

                               val mapX :Double = userLocationRequest.mapX
                               val mapY :Double = userLocationRequest.mapY
}