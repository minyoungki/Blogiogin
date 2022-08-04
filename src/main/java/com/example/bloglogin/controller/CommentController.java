package com.example.bloglogin.controller;


import com.example.bloglogin.dto.BlogRequestDto;
import com.example.bloglogin.dto.CommentRequestDto;
import com.example.bloglogin.dto.ResponseDto;
import com.example.bloglogin.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentService commentService;


    @PostMapping("/api/comment")
    public ResponseDto<?> createComment(@RequestBody CommentRequestDto requestDto) {
        return commentService.getAllComment(requestDto);
    }

    @PutMapping("/api/comment/{id}")
    public ResponseDto<?> updateComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto) {
        return commentService.updateComment(id, commentRequestDto);
    }

    @DeleteMapping("/api/comment/{id}")
    public ResponseDto<?> deleteComment(@PathVariable Long id) {
        return commentService.deleteComment(id);
    }

}
