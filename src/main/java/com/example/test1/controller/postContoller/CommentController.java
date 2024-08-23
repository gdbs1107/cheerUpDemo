package com.example.test1.controller.postContoller;

import com.example.test1.apiPayload.ApiResponse;
import com.example.test1.controller.dto.postDTO.CommentRequestDTO;
import com.example.test1.service.commentService.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;


    //-------------커뮤니티 댓글----------------
    @PostMapping("/{communityId}")
    public ApiResponse<String> postComment(@PathVariable Long communityId,
                                           @RequestBody CommentRequestDTO.CommentPostRequestDTO request,
                                           @AuthenticationPrincipal UserDetails userDetails){

        commentService.postComment(communityId,request, userDetails.getUsername());

        return ApiResponse.onSuccess("saved");
    }

    @PutMapping("/{commentId}")
    public ApiResponse<String> updateComment(@PathVariable Long commentId,
                                             @RequestBody CommentRequestDTO.CommentUpdateRequestDTO request,
                                             @AuthenticationPrincipal UserDetails userDetails){

        commentService.updateComment(commentId, request, userDetails.getUsername());
        return ApiResponse.onSuccess("updated");

    }
    @DeleteMapping("/{commentId}")
    public ApiResponse<String> deleteComment(@PathVariable Long commentId,
                                             @AuthenticationPrincipal UserDetails userDetails){

        commentService.DeleteComment(commentId, userDetails.getUsername());
        return ApiResponse.onSuccess("deleted");
    }


}
