package com.example.footballbackend.core.comments.web.contract;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Создание комментария")
public record CommentReq(
        @NotBlank
        String content,
        @NotNull
        Integer newsId,
        @NotNull
        Integer authorId
) {
}
