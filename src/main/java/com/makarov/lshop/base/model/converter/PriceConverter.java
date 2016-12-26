package com.makarov.lshop.base.model.converter;


import javax.persistence.AttributeConverter;
import java.math.BigDecimal;

@javax.persistence.Converter
public class PriceConverter implements AttributeConverter<String, BigDecimal> {


    @Override
    public BigDecimal convertToDatabaseColumn(String s) {
        return new BigDecimal(s);
    }

    @Override
    public String convertToEntityAttribute(BigDecimal bigDecimal) {
        return bigDecimal.toString();
    }
}
