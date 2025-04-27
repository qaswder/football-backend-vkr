package com.example.footballbackend.core.comments.converter;

import com.example.footballbackend.core.comments.dto.Comment;
import com.example.footballbackend.core.comments.web.contract.CommentView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommentToCommentView implements Converter<Comment, CommentView> {
    @Override
    public CommentView convert(Comment source) {
        return new CommentView(
                source.getId(),
                source.getContent(),
                source.getPublicationDate(),
                source.getAuthor().getUsername()
        );
    }
}
