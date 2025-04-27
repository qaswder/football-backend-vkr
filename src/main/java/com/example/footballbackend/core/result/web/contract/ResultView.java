package com.example.footballbackend.core.result.web.contract;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Команда")
public record ResultView(
        @Schema(description = "Идентификатор")
        Integer id,
        @Schema(description = "Название команды")
        String teamName,
        @Schema(description = "Позиция")
        Integer position,
        @Schema(description = "Сыграно матчей")
        Integer played,
        @Schema(description = "Победы")
        Integer wins,
        @Schema(description = "Ничьи")
        Integer draws,
        @Schema(description = "Проигрыши")
        Integer losses,
        @Schema(description = "Очки")
        Integer points,
        @Schema(description = "Идентификатор команды")
        Integer teamId,
        @Schema(description = "Идентификатор турнира")
        Integer tournamentId
) {
}
