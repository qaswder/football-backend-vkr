package com.example.footballbackend.core.statistics.web.contract;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Статистика игрока")
public record StatisticsView(
        @Schema(description = "Идентификатор")
        Integer id,
        @Schema(description = "Идентификатор игрока")
        Integer playerId,
        @Schema(description = "Идентификатор матча")
        Integer matchId,
        @Schema(description = "Позиция игрока")
        String playerPosition,
        @Schema(description = "Имя игрока")
        String playerName,
        @Schema(description = "Голы")
        Integer goals,
        @Schema(description = "Ассисты")
        Integer assists,
        @Schema(description = "Желтые карточки")
        Integer yellowCards,
        @Schema(description = "Красные карточки")
        Integer redCards,
        @Schema(description = "Сыграно минут")
        Integer playedMinutes,
        @Schema(description = "Удары")
        Integer shots,
        @Schema(description = "Пасы")
        Integer passes,
        @Schema(description = "Сезон")
        LocalDate season
) {
}
