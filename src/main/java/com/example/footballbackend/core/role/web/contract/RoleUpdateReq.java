package com.example.footballbackend.core.role.web.contract;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Изменение роли пользователя")
public record RoleUpdateReq(
        @NotBlank
        String roleName,
        @NotBlank
        String description
) {
}
