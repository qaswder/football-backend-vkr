package com.example.footballbackend.core.news.converter;

import com.example.footballbackend.core.news.dto.News;
import com.example.footballbackend.core.news.web.contract.NewsView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NewsToNewsView implements Converter<News, NewsView> {
    @Override
    public NewsView convert(News source) {
        String username = source.getAuthor().getUsername();

        return new NewsView(
                source.getId(),
                source.getTitle(),
                source.getContent(),
                source.getImageUrl(),
                source.getPublicationDate(),
                username
        );
    }
}
