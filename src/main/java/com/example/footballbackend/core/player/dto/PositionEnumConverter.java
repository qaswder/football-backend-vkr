package com.example.footballbackend.core.player.dto;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class PositionEnumConverter implements AttributeConverter<PositionEnum, String>{
    @Override
    public String convertToDatabaseColumn(PositionEnum positionEnum) {
        if (positionEnum == null) {
            return null;
        }
        return positionEnum.getCode();
    }

    @Override
    public PositionEnum convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return Stream.of(PositionEnum.values())
                .filter(c -> c.getCode().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
