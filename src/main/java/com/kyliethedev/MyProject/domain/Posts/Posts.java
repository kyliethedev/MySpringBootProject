package com.kyliethedev.MyProject.domain.Posts;


import com.kyliethedev.MyProject.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    private String content;

    @Builder
    public Posts(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }
}
