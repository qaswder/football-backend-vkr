package com.example.footballbackend.core.role.web.contract;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Создание роли пользователя")
public record RoleCreateReq(
        @NotBlank
        String roleName,
        @NotBlank
        String description
) {
}
