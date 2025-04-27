package com.example.footballbackend.core.player.web.contract;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Игрок")
public record PlayerView(
        @Schema(description = "Идентификатор")
        Integer id,
        @Schema(description = "Фамилия")
        String surname,
        @Schema(description = "Имя")
        String name,
        @Schema(description = "Отчество")
        String patronymic,
        @Schema(description = "Дата рождения")
        LocalDate birthdate,
        @Schema(description = "Национальность")
        String nationality,
        @Schema(description = "Позиция")
        String position,
        @Schema(description = "Номер игрока")
        Integer playerNumber,
        @Schema(description = "Команда")
        String teamName,
        @Schema(description = "Фото игрока")
        String photoUrl
) {
}
