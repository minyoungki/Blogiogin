package com.example.bloglogin.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {

    private Long commentId;
    private String comment;
    private String author;
}
