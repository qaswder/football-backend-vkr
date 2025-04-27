package com.example.footballbackend.core.match.dto;

import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.stream.Stream;

public enum MatchStatusEnum {
    ENDED("Завершен"),
    AWAIT("Предстоит");

    MatchStatusEnum(String code) {
        this.code = code;
    }

    private final String code;

    public String getCode() {
        return code;
    }

    public static MatchStatusEnum getStaffByCode(@Nullable String code) {
        return Stream.of(MatchStatusEnum.values())
                .filter(e -> Objects.equals(e.getCode(), code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
