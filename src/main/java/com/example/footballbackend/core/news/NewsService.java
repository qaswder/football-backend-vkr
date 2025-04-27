package com.example.footballbackend.core.news;

import com.example.footballbackend.core.news.dto.News;
import com.example.footballbackend.core.news.dto.NewsRepo;
import com.example.footballbackend.error.ConflictResourceException;
import com.example.footballbackend.util.MessageUtil;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class NewsService {
    private final NewsRepo newsRepo;
    private final MessageUtil messageUtil;

    public NewsService(NewsRepo newsRepo,
                       MessageUtil messageUtil) {
        this.newsRepo = newsRepo;
        this.messageUtil = messageUtil;
    }

    @Transactional(readOnly = true)
    public Page<News> getAllNews(Pageable pageable){
        return newsRepo.findAllNews(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<News> getNewsById(@NonNull Integer id){
        return newsRepo.findById(id);
    }

    @Transactional
    public News saveNews(@NonNull News news){
        try{
            return newsRepo.save(news);
        }catch (DataIntegrityViolationException e){
            throw new ConflictResourceException(e.getMessage());
        }
    }

    @Transactional
    public News getReferenceOrNew(@Nullable Integer id){
        return id == null ? new News() : newsRepo.getReferenceById(id);
    }

    @Transactional
    public void deleteNewsById(@NonNull Integer id){
        newsRepo.deleteById(id);
    }
}
