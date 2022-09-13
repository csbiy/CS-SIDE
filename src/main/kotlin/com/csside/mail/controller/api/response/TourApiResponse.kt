package com.csside.mail.controller.api.response

import com.csside.mail.controller.api.response.body.Body


data class TourApiResponse<T>(val response: Payload<T>) {

}
