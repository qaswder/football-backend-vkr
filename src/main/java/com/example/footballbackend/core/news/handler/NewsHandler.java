package com.example.footballbackend.core.news.handler;

import com.example.footballbackend.core.news.NewsService;
import com.example.footballbackend.core.news.converter.NewsConverter;
import com.example.footballbackend.core.news.dto.News;
import com.example.footballbackend.core.news.web.contract.NewsReq;
import com.example.footballbackend.core.news.web.contract.NewsView;
import com.example.footballbackend.core.news.web.contract.NewsViewWithComments;
import com.example.footballbackend.core.user.UserService;
import com.example.footballbackend.core.user.dto.User;
import com.example.footballbackend.error.NotFoundException;
import com.example.footballbackend.util.MessageUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class NewsHandler {
    private final NewsConverter converter;
    private final NewsService service;
    private final UserService userService;
    private final MessageUtil messageUtil;

    public NewsHandler(NewsConverter converter,
                       NewsService service,
                       UserService userService,
                       MessageUtil messageUtil) {
        this.converter = converter;
        this.service = service;
        this.userService = userService;
        this.messageUtil = messageUtil;
    }

    public NewsViewWithComments handlerGetNewsById(@NonNull Integer id) {
        return converter.toViewWithComments(
                service.getNewsById(id)
                        .orElseThrow(() ->
                                new NotFoundException(messageUtil.getMessage("news.id.not-found", id)))
        );
    }

    public Page<NewsView> handlerGetAllNews(@NonNull Pageable pageable) {
        Page<News> news = service.getAllNews(pageable);
        return news.map(converter::toView);
    }

    public NewsView handlerCreateNews(@NonNull NewsReq req) {
        final User author = userService.getUserById(req.authorId())
                .orElseThrow(() ->
                        new NotFoundException(messageUtil.getMessage("user.id.not-found", req.authorId()))
                );
        final News news = new News();

        news.setTitle(req.title());
        news.setContent(req.content());
        news.setImageUrl(req.imageUrl());
        news.setAuthor(author);
        news.setPublicationDate(LocalDateTime.now());

        return converter.toView(
                service.saveNews(news)
        );
    }

    public void handlerDeleteNewsById(@NonNull Integer id) {
        service.deleteNewsById(id);
    }
}
