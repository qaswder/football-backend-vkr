package com.example.footballbackend.core.news.dto;

import com.example.footballbackend.core.comments.dto.Comment;
import com.example.footballbackend.core.user.dto.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "fc_news")
public class News {
    @Id
    @SequenceGenerator(name = "seq_gen_news", sequenceName = "seq_news", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen_news")
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "publication_date")
    private LocalDateTime publicationDate;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User author;

    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();
}
