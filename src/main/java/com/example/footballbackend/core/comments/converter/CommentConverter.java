package com.example.footballbackend.core.comments.converter;

import com.example.footballbackend.core.comments.dto.Comment;
import com.example.footballbackend.core.comments.web.contract.CommentView;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter {
    private final CommentToCommentView toCommentView;

    public CommentConverter(CommentToCommentView toCommentView){
        this.toCommentView = toCommentView;
    }

    public CommentView toView(@NonNull Comment comment){
        return toCommentView.convert(comment);
    }
}
