package com.csside.mail.controller.api.response

import com.csside.mail.controller.api.response.body.Body


data class Payload<T>(val header: Header ,
                      val body: Body<T>)