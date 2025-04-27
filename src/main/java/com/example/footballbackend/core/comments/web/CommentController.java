package com.example.footballbackend.core.comments.web;

import com.example.footballbackend.core.comments.handler.CommentHandler;
import com.example.footballbackend.core.comments.web.contract.CommentView;
import com.example.footballbackend.core.result.handler.ResultHandler;
import com.example.footballbackend.core.result.web.contract.ResultView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
@Tag(name = "Комментарии")
public class CommentController {
    private final CommentHandler handler;

    public CommentController(CommentHandler handler) {
        this.handler = handler;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    @Operation(summary = "Получение комментария по id")
    public CommentView getCommentById(@PathVariable @NotNull Integer id) {
        return handler.handlerGetCommentById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @Operation(summary = "Получение всех комментариев")
    public Page<CommentView> getAllComments(@RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
                                            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, size);
        return handler.handlerGetAllComment(pageable);
    }
}
