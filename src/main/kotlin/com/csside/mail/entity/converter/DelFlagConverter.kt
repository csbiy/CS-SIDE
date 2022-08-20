package com.csside.mail.entity.converter

import com.csside.mail.enumeration.DelFlag
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter
class DelFlagConverter : AttributeConverter<DelFlag, String> {
    override fun convertToDatabaseColumn(attribute: DelFlag): String {
        return attribute.value;
    }

    override fun convertToEntityAttribute(dbData: String): DelFlag {
        return enumValueOf<DelFlag>(dbData);
    }

}