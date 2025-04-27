package com.example.footballbackend.core.team.web.contract;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Создание/изменение команды")
public record TeamReq(
        @NotBlank
        String teamName,
        @NotBlank
        String teamShortName,
        @NotBlank
        String stadium,
        @NotBlank
        String logoUrl
) {
}
