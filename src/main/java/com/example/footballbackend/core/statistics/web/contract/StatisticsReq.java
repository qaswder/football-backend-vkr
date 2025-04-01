package com.example.footballbackend.core.statistics.web.contract;

import com.example.footballbackend.util.validation.OnCreate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Schema(description = "Создание/изменение статистики игрока")
public record StatisticsReq(
        @NotNull(groups = {OnCreate.class})
        Integer playerId,
        @NotNull(groups = {OnCreate.class})
        Integer goals,
        @NotNull(groups = {OnCreate.class})
        Integer assists,
        @NotNull(groups = {OnCreate.class})
        Integer yellowCards,
        @NotNull(groups = {OnCreate.class})
        Integer redCards,
        @NotNull(groups = {OnCreate.class})
        LocalDate season
) {
}
