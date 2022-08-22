package com.csside.mail.enumeration

import javax.persistence.AttributeConverter
import javax.persistence.Converter

enum class DelFlag (val value:String){
    Y("Y"),N("N")
}

@Converter
class DelFlagConverter : AttributeConverter<DelFlag, String> {
    override fun convertToDatabaseColumn(attribute: DelFlag): String {
        return attribute.value;
    }
    override fun convertToEntityAttribute(dbData: String): DelFlag {
        return enumValueOf<DelFlag>(dbData);
    }
}