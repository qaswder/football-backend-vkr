package com.example.footballbackend.core.tournament.web.contract;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Команда")
public record TournamentView(
        @Schema(description = "Идентификатор")
        Integer id,
        @Schema(description = "Название турнира")
        String tournamentName,
        @Schema(description = "Сезон")
        String season
) {
}
