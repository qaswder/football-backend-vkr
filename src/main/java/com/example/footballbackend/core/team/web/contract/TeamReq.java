package com.example.footballbackend.core.team.web.contract;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Создание/изменение команды")
public record TeamReq(
        @NotBlank
        String teamName,
        @NotNull
        Integer coachId,
        @NotBlank
        String league
) {
}
