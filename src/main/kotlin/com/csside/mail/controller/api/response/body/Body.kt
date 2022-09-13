package com.csside.mail.controller.api.response.body

data class Body<T>(
    val numOfRows :Int ,
    val pageNo:Int ,
    val totalCount :Int,
    val items:ItemWrapper<T>
    )

data class ItemWrapper<T> (val item:List<T>)