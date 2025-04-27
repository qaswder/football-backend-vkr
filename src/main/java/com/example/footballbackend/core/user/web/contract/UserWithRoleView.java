package com.example.footballbackend.core.user.web.contract;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Пользователь с ролью")
public record UserWithRoleView(
        @Schema(description = "Идентификатор")
        Integer id,
        @Schema(description = "Имя пользователя")
        String username,
        @Schema(description = "Почта")
        String email,
        @Schema(description = "Пароль")
        String password,
        @Schema(description = "Роль")
        String roleDescription
) {
}
