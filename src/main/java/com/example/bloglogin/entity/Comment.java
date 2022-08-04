package com.example.bloglogin.entity;

import com.example.bloglogin.dto.BlogRequestDto;
import com.example.bloglogin.dto.CommentRequestDto;
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
public class Comment extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;  // 아이디를 받는다.

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private String author;

    public Comment(CommentRequestDto commentRequestDto) {
        this.commentId = commentRequestDto.getCommentId();
        this.comment = commentRequestDto.getComment();
    }

    public Comment(CommentRequestDto commentRequestDto, String author) {
        this.commentId = commentRequestDto.getCommentId();
        this.comment = commentRequestDto.getComment();
        this.author = author;
    }

    public void update(CommentRequestDto commentRequestDto) {
        this.comment = commentRequestDto.getComment();
    }
}
