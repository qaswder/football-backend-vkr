package com.example.footballbackend.core.user.web.contract;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Пользователь")
public record UserView(
        @Schema(description = "Идентификатор")
        Integer id,
        @Schema(description = "Имя пользователя")
        String username,
        @Schema(description = "Почта")
        String email,
        @Schema(description = "Логин")
        String login
) {
}
