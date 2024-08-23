package com.example.test1.service.commentService;

import com.example.test1.controller.dto.postDTO.CommentRequestDTO;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {


    void postComment(Long id, CommentRequestDTO.CommentPostRequestDTO request, String username);

    void updateComment(Long commentId, CommentRequestDTO.CommentUpdateRequestDTO request, String username);

    void DeleteComment(Long id, String username);
}
