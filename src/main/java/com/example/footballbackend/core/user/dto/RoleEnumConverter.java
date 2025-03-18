package com.example.footballbackend.core.user.dto;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class RoleEnumConverter implements AttributeConverter<RoleEnum, String> {
    @Override
    public String convertToDatabaseColumn(RoleEnum roleEnum) {
        if (roleEnum == null) {
            return null;
        }
        return roleEnum.getCode();
    }

    @Override
    public RoleEnum convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return Stream.of(RoleEnum.values())
                .filter(c -> c.getCode().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
