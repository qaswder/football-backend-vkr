package com.example.footballbackend.core.news.web.contract;

import com.example.footballbackend.core.comments.web.contract.CommentView;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "Новость с комментариями")
public record NewsViewWithComments(
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
        String username,
        @Schema(description = "Комментарии")
        List<CommentView> comments
) {

}
