package com.example.footballbackend.core.coach.web.contract;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Тренер")
public record CoachView(
        @Schema(description = "Идентификатор")
        Integer id,
        @Schema(description = "Фамилия")
        String surname,
        @Schema(description = "Имя")
        String name,
        @Schema(description = "Отчество")
        String patronymic
) {
}
