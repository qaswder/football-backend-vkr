package com.example.footballbackend.core.match.web.contract;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Матч")
public record MatchView(
        @Schema(description = "Идентификатор")
        Integer id,
        @Schema(description = "Название домашней команды")
        String teamHomeName,
        @Schema(description = "Название гостевой команды")
        String teamAwayName,
        @Schema(description = "Счет")
        String score,
        @Schema(description = "Дата и время")
        LocalDateTime date
) {
}
