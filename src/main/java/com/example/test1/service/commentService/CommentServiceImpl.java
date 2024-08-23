package com.example.test1.service.commentService;

import com.example.test1.Repository.postRepository.CommentRepository;
import com.example.test1.Repository.postRepository.CommunityRepository;
import com.example.test1.Repository.UserRepository;
import com.example.test1.controller.dto.postDTO.CommentRequestDTO;
import com.example.test1.domain.UserEntity;
import com.example.test1.domain.postEntity.Comment;
import com.example.test1.domain.postEntity.Community;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommunityRepository communityRepository;
    private final UserRepository userRepository;


    @Override
    public void postComment(Long id,CommentRequestDTO.CommentPostRequestDTO request, String username){

        Community findCommunity = communityRepository.findById(id).orElseThrow(
                ()->new RuntimeException("cannot find community")
        );

        UserEntity findUser=userRepository.findByUsername(username).orElseThrow(
                ()->new RuntimeException("cannot find users")
        );


        Comment newComment = Comment.builder()
                .content(request.getContent())
                .community(findCommunity)
                .userEntity(findUser)
                .build();

        commentRepository.save(newComment);
    }

    @Override
    public void updateComment(Long commentId, CommentRequestDTO.CommentUpdateRequestDTO request, String username){

        UserEntity findUser=userRepository.findByUsername(username).orElseThrow(
                ()->new RuntimeException("cannot find user")
        );

        Comment existingComment= commentRepository.findByIdAndUserEntity(commentId,findUser).orElseThrow(
                ()->new RuntimeException("cannot find comment")
        );

        userValidate(existingComment,findUser);

        existingComment.update(request.getContent());
        commentRepository.save(existingComment);

    }

    @Override
    public void DeleteComment(Long commentId, String username){

        UserEntity findUser=userRepository.findByUsername(username).orElseThrow();

        Comment existingComment = commentRepository.findById(commentId).orElseThrow();

        userValidate(existingComment,findUser);
        commentRepository.delete(existingComment);

    }







    private static void userValidate(Comment existingComment, UserEntity user) {
        if (!existingComment.getUserEntity().equals(user)) {
            throw new RuntimeException("is not not yours");
        }
}

}
