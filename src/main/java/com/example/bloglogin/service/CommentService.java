package com.example.bloglogin.service;


import com.example.bloglogin.dto.BlogRequestDto;
import com.example.bloglogin.dto.CommentRequestDto;
import com.example.bloglogin.dto.ResponseDto;
import com.example.bloglogin.entity.Blog;
import com.example.bloglogin.entity.Comment;
import com.example.bloglogin.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public Long save(CommentRequestDto requestDto) {
        Comment comment = new Comment(requestDto);
        commentRepository.save(comment);
        return comment.getCommentId();
    }

    @Transactional
    public ResponseDto<?> getAllComment(CommentRequestDto requestDto) {
        return ResponseDto.success(commentRepository.findById(save(requestDto)));
    }

    @Transactional
    public ResponseDto<Comment> updateComment(Long id, CommentRequestDto requestDto) {
        // 데이터 타입 ResponseDto<?>으로 매개변수 Long id, BlogRequestDto requestDto를 받는다.

        Optional<Comment> optionalComment = commentRepository.findById(id);
        //데이터타입 Optional<Blog> 인 optionalBlog에 BlogRepository에서 id를 찾아 너어 준다.

        if (optionalComment.isEmpty()) {
            return ResponseDto.fail("NULL_Blog_ID", "Blog id isn't exist");
        }
        // 만약 optionalBlog가 isEmpty() = 비어 있다면 ResponseDto에 fail에 값을 전달.

        Comment comment = optionalComment.get();  //아니라면 optionalBlog를 Blog에 넣음
        comment.update(requestDto);        //update 실행

        return ResponseDto.success(comment);
        // 아니라면 ResponseDto에 success에 Blog을 전달
    }
    @Transactional
    public ResponseDto<?> deleteComment(Long id) {  //위랑 거의 동일
        Optional<Comment> optionalComment = commentRepository.findById(id);

        if (optionalComment.isEmpty()) {
            return ResponseDto.fail("NOT_FOUND", "comment id is not exist");
        }

       Comment comment = optionalComment.get();

        commentRepository.delete(comment);

        return ResponseDto.success(true);
    }
}
