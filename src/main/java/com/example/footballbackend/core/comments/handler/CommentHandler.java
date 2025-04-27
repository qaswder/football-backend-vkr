package com.example.footballbackend.core.comments.handler;

import com.example.footballbackend.core.comments.CommentService;
import com.example.footballbackend.core.comments.converter.CommentConverter;
import com.example.footballbackend.core.comments.dto.Comment;
import com.example.footballbackend.core.comments.web.contract.CommentReq;
import com.example.footballbackend.core.comments.web.contract.CommentView;
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
public class CommentHandler {
    private final CommentConverter converter;
    private final CommentService service;
    private final UserService userService;
    private final NewsService newsService;
    private final MessageUtil messageUtil;

    public CommentHandler(CommentConverter converter,
                       CommentService service,
                       NewsService newsService,
                       UserService userService,
                       MessageUtil messageUtil) {
        this.converter = converter;
        this.service = service;
        this.userService = userService;
        this.newsService = newsService;
        this.messageUtil = messageUtil;
    }

    public CommentView handlerGetCommentById(@NonNull Integer id) {
        return converter.toView(
                service.getCommentById(id)
                        .orElseThrow(() ->
                                new NotFoundException(messageUtil.getMessage("comment.id.not-found", id)))
        );
    }

    public Page<CommentView> handlerGetAllComment(@NonNull Pageable pageable) {
        Page<Comment> comments = service.getAllComments(pageable);
        return comments.map(converter::toView);
    }

    public CommentView handlerCreateComment(@NonNull CommentReq req) {
        final User author = userService.getUserById(req.authorId())
                .orElseThrow(() ->
                        new NotFoundException(messageUtil.getMessage("user.id.not-found", req.authorId()))
                );
        final News news = newsService.getNewsById(req.newsId())
                .orElseThrow(() ->
                        new NotFoundException(messageUtil.getMessage("news.id.not-found", req.newsId()))
                );
        final Comment comment = new Comment();

        comment.setContent(req.content());
        comment.setNews(news);
        comment.setAuthor(author);
        comment.setPublicationDate(LocalDateTime.now());

        return converter.toView(
                service.saveComment(comment)
        );
    }

    public void handlerDeleteCommentById(@NonNull Integer id) {
        service.deleteCommentById(id);
    }
}
