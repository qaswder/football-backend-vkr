package com.example.footballbackend.core.tournament.web.contract;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Создание/изменение турнира")
public record TournamentReq(
        @NotBlank
        String tournamentName,
        @NotBlank
        String season
) {
}
