package com.example.footballbackend.core.role.web.contract;

import io.swagger.v3.oas.annotations.media.Schema;

public record RoleView(
        @Schema(description = "Идентификатор")
        Integer id,
        @Schema(description = "Название")
        String description) {
}
