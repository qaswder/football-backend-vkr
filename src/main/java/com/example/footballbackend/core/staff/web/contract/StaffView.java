package com.example.footballbackend.core.staff.web.contract;


import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Персонал")
public record StaffView(
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
        @Schema(description = "Название команды")
        String teamName,
        @Schema(description = "Фото персонала")
        String photoUrl,
        @Schema(description = "Роль")
        String staffRole
) {
}
