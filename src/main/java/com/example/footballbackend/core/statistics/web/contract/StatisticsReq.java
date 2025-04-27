package com.example.footballbackend.core.statistics.web.contract;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Schema(description = "Создание/изменение статистики игрока")
public record StatisticsReq(
        @NotNull
        Integer playerId,
        @NotNull
        Integer matchId,
        @NotNull
        Integer goals,
        @NotNull
        Integer assists,
        @NotNull
        Integer yellowCards,
        @NotNull
        Integer redCards,
        @NotNull
        Integer playedMinutes,
        @NotNull
        Integer shots,
        @NotNull
        Integer passes,
        @NotNull
        LocalDate season
) {
}
