package com.example.footballbackend.core.user.web.contract;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Создание пользователя")
public record UserCreateReq(
        @NotBlank
        String username,
        @NotBlank
        String email,
        @NotBlank
        String password
) {
}
