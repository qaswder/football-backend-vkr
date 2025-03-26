package com.example.footballbackend.core.coach.web.contract;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

@Schema(description = "Создание/изменение тренера")
public record CoachReq(
        @NotBlank
        String surname,
        @NotBlank
        String name,
        @NotBlank
        String patronymic
) {
}
