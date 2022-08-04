package com.example.bloglogin.entity;


import com.example.bloglogin.dto.BlogRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Blog extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 아이디를 받는다.

    @Column(nullable = false)
    private String title;  //

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private String author;

    @JsonIgnore
    @Column(nullable = false)
    private String password;


    public Blog(BlogRequestDto blogRequestDto) {
        this.title = blogRequestDto.getTitle();
        this.content = blogRequestDto.getContent();
        this.author = blogRequestDto.getAuthor();
        this.password = blogRequestDto.getPassword();
    }

    public void update(BlogRequestDto blogRequestDto) {
        this.title = blogRequestDto.getTitle();
        this.content = blogRequestDto.getContent();
        this.author = blogRequestDto.getAuthor();
        this.password = blogRequestDto.getPassword();
    }

}
