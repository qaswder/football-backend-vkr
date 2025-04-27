package com.example.footballbackend.core.news.converter;

import com.example.footballbackend.core.comments.converter.CommentConverter;
import com.example.footballbackend.core.comments.web.contract.CommentView;
import com.example.footballbackend.core.news.dto.News;
import com.example.footballbackend.core.news.web.contract.NewsViewWithComments;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NewsToNewsViewWithComments implements Converter<News, NewsViewWithComments> {
    private final CommentConverter converter;

    public NewsToNewsViewWithComments(CommentConverter converter){
        this.converter = converter;
    }

    @Override
    public NewsViewWithComments convert(News source) {
        List<CommentView> commentViews = source.getComments()
                .stream().map(converter::toView)
                .toList();

        return new NewsViewWithComments(
                source.getId(),
                source.getTitle(),
                source.getContent(),
                source.getImageUrl(),
                source.getPublicationDate(),
                source.getAuthor().getUsername(),
                commentViews
        );
    }
}
