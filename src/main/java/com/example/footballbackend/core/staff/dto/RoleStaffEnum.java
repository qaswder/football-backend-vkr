package com.example.footballbackend.core.staff.dto;

import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.stream.Stream;

public enum RoleStaffEnum {
    COACH("Тренер"),
    MEDIC("Врач"),
    PRESIDENT("Президент клуба");

    RoleStaffEnum(String code) {
        this.code = code;
    }

    private final String code;

    public String getCode() {
        return code;
    }

    public static RoleStaffEnum getStaffByCode(@Nullable String code) {
        return Stream.of(RoleStaffEnum.values())
                .filter(e -> Objects.equals(e.getCode(), code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
