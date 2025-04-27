package com.example.footballbackend.core.news.web.contract;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Schema(description = "Новость")
public record NewsView(
        @Schema(description = "Идентификатор")
        Integer id,
        @Schema(description = "Название")
        String title,
        @Schema(description = "Новость")
        String content,
        @Schema(description = "Фото")
        String imageUrl,
        @Schema(description = "Дата публикации")
        LocalDateTime publicationDate,
        @Schema(description = "Автор")
        String username
) {
}
