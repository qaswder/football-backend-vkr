package com.example.footballbackend.core.news.web.contract;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Schema(description = "Создание/изменение новости")
public record NewsReq(
        @NotBlank
        String title,
        @NotBlank
        String content,
        String imageUrl,
        @NotNull
        Integer authorId
) {
}
