package com.example.footballbackend.core.comments;

import com.example.footballbackend.core.comments.dto.Comment;
import com.example.footballbackend.core.comments.dto.CommentRepo;
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
public class CommentService {
    private final CommentRepo commentRepo;
    private final MessageUtil messageUtil;

    public CommentService(CommentRepo commentRepo,
                       MessageUtil messageUtil) {
        this.commentRepo = commentRepo;
        this.messageUtil = messageUtil;
    }

    @Transactional(readOnly = true)
    public Page<Comment> getAllComments(Pageable pageable){
        return commentRepo.findAllComments(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<Comment> getCommentById(@NonNull Integer id){
        return commentRepo.findById(id);
    }

    @Transactional
    public Comment saveComment(@NonNull Comment comment){
        try{
            return commentRepo.save(comment);
        }catch (DataIntegrityViolationException e){
            throw new ConflictResourceException(e.getMessage());
        }
    }

    @Transactional
    public Comment getReferenceOrNew(@Nullable Integer id){
        return id == null ? new Comment() : commentRepo.getReferenceById(id);
    }

    @Transactional
    public void deleteCommentById(@NonNull Integer id){
        commentRepo.deleteById(id);
    }
}
