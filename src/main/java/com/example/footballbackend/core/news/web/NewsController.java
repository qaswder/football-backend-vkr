package com.example.footballbackend.core.news.web;

import com.example.footballbackend.core.news.handler.NewsHandler;
import com.example.footballbackend.core.news.web.contract.NewsView;
import com.example.footballbackend.core.news.web.contract.NewsViewWithComments;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
@Tag(name = "Новости")
public class NewsController {
    private final NewsHandler handler;

    public NewsController(NewsHandler handler) {
        this.handler = handler;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    @Operation(summary = "Получение новости по id")
    public NewsViewWithComments getNewsById(@PathVariable @NotNull Integer id) {
        return handler.handlerGetNewsById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @Operation(summary = "Получение всех комментариев")
    public Page<NewsView> getAllNews(@RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
                                     @RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, size);
        return handler.handlerGetAllNews(pageable);
    }
}
