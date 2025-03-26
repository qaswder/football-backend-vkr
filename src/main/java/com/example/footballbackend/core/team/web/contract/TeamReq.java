package com.example.footballbackend.core.team.web.contract;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.springframework.lang.NonNull;

import java.time.LocalDate;

@Schema(description = "Создание/изменение команды")
public record TeamReq(
        @NotBlank
        String teamName,
        @NonNull
        Integer coachId,
        @NotBlank
        String league
) {
}
