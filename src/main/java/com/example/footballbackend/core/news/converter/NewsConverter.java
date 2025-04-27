package com.example.footballbackend.core.news.converter;

import com.example.footballbackend.core.news.dto.News;
import com.example.footballbackend.core.news.web.contract.NewsView;
import com.example.footballbackend.core.news.web.contract.NewsViewWithComments;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class NewsConverter {
    private final NewsToNewsView toNewsView;
    private final NewsToNewsViewWithComments toNewsViewWithComments;

    public NewsConverter(NewsToNewsView toNewsView,
                         NewsToNewsViewWithComments toNewsViewWithComments){
        this.toNewsView = toNewsView;
        this.toNewsViewWithComments = toNewsViewWithComments;
    }

    public NewsView toView(@NonNull News news){
        return toNewsView.convert(news);
    }

    public NewsViewWithComments toViewWithComments(@NonNull News news){
        return toNewsViewWithComments.convert(news);
    }

}
