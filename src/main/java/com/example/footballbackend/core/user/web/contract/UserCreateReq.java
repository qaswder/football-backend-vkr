package com.example.footballbackend.core.user.web.contract;

import com.example.footballbackend.core.user.dto.RoleEnum;
import com.example.footballbackend.util.ValidEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Создание пользователя")
public record UserCreateReq(
        @NotBlank
        String username,
        @NotBlank
        String login,
        @NotBlank
        String email,
        @NotBlank
        String password,
//        @ValidEnum(enumClass = RoleEnum.class, message = "Роль должна быть одной из: ADMIN, COACH, USER")
        String userRole
) {
}
