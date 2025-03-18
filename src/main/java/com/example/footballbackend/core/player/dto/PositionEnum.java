package com.example.footballbackend.core.player.dto;

import com.example.footballbackend.core.user.dto.RoleEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.stream.Stream;

public enum PositionEnum {
    GOALKEEPER("Вратарь"),
    CENTRAL_DEFENDER("Центральный защитник"),
    RIGHT_DEFENDER("Правый защитник"),
    LEFT_DEFENDER("Левый защитник"),
    MIDFIELDER("Полузащитник"),
    FORWARD("Нападающий");

    PositionEnum(String code) {
        this.code = code;
    }

    private final String code;

    public String getCode() {
        return code;
    }

    public static PositionEnum getGenreByCode(@Nullable String code) {
        return Stream.of(PositionEnum.values())
                .filter(e -> Objects.equals(e.getCode(), code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
