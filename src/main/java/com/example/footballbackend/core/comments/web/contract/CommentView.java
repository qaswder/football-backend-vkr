package com.example.footballbackend.core.comments.web.contract;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Комментарий")
public record CommentView(
        @Schema(description = "Идентификатор")
        Integer id,
        @Schema(description = "Комментарий")
        String content,
        @Schema(description = "Дата публикации")
        LocalDateTime publicationDate,
        @Schema(description = "Автор")
        String username
) {
}
