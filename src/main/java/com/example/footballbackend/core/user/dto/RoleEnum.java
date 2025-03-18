package com.example.footballbackend.core.user.dto;

import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.stream.Stream;

public enum RoleEnum {
    ADMIN("Администратор"),
    COACH("Тренер"),
    USER("Пользователь");

    RoleEnum(String code) {
        this.code = code;
    }

    private final String code;

    public String getCode() {
        return code;
    }

    public static RoleEnum getRoleByCode(@Nullable String code) {
        return Stream.of(RoleEnum.values())
                .filter(e -> Objects.equals(e.getCode(), code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
