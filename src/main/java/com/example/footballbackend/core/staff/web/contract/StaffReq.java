package com.example.footballbackend.core.staff.web.contract;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Schema(description = "Создание/изменение персонала")
public record StaffReq(
        @NotBlank
        String surname,
        @NotBlank
        String name,
        @NotBlank
        String patronymic,
        @NotNull
        LocalDate birthdate,
        @NotBlank
        String staffRole,
        String photoUrl,
        @NotNull
        Integer teamId
) {
}
