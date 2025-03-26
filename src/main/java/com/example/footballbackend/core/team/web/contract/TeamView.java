package com.example.footballbackend.core.team.web.contract;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Команда")
public record TeamView(
        @Schema(description = "Идентификатор")
        Integer id,
        @Schema(description = "Название")
        String teamName,
        @Schema(description = "Имя тренера")
        String coachName,
        @Schema(description = "Лига")
        String league
) {
}
