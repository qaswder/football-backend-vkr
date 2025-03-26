package com.example.footballbackend.core.player.web.contract;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Schema(description = "Создание/изменение игрока")
public record PlayerReq(
        @NotBlank
        String surname,
        @NotBlank
        String name,
        @NotBlank
        String patronymic,
        @NotNull
        LocalDate birthdate,
        @NotBlank
        String position
) {
}
