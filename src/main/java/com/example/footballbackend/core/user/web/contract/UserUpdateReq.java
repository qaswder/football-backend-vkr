package com.example.footballbackend.core.user.web.contract;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Изменение пользователя")
public record UserUpdateReq(
        @NotNull
        String username
) {
}
