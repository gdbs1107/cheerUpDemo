package com.example.test1.service.postPortfolioService;

import com.example.test1.Repository.postRepository.CommentRepository;
import com.example.test1.Repository.postRepository.LikeRepository;
import com.example.test1.Repository.postRepository.PostPortfolioRepository;
import com.example.test1.Repository.UserRepository;
import com.example.test1.controller.dto.postDTO.PortfolioResponseDTO;
import com.example.test1.controller.dto.postDTO.PostPortfolioRequestDTO;
import com.example.test1.converter.PostPortfolioConverter;
import com.example.test1.domain.UserEntity;
import com.example.test1.domain.postEntity.Likes;
import com.example.test1.domain.postEntity.PostPortfolio;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostPortfolioServiceImpl implements PostPortfolioService {

    private final PostPortfolioRepository postPortfolioRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;



    @Override
    public void postPortfolio(PostPortfolioRequestDTO.postPortfolioRequestDTO request, String username){

        UserEntity user = userRepository.findByUsername(username).orElseThrow(
                ()->new RuntimeException("user not found"));

        PostPortfolio newPostPortfolio = PostPortfolioConverter.toPostPortfolio(request,user);
        postPortfolioRepository.save(newPostPortfolio);

    }



    @Override
    public void updatePortfolio(Long portfolioId, PostPortfolioRequestDTO.postPortfolioRequestDTO request, String username){

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Find the community post that is mapped to the user
        PostPortfolio existingPortfolio = postPortfolioRepository.findByIdAndUserEntity(portfolioId, user)
                .orElseThrow(() -> new RuntimeException("postPortfolio not found"));

        // Update the community post
        existingPortfolio.update(request.getTitle(), request.getContent());

        postPortfolioRepository.save(existingPortfolio);
    }



    @Override
    public void deletePortfolio(Long portfolioId, String username){

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        PostPortfolio existingPostPortfolio = postPortfolioRepository.findById(portfolioId)
                .orElseThrow(() -> new RuntimeException("portfolio not found"));

        userValidate(existingPostPortfolio, user);

        postPortfolioRepository.delete(existingPostPortfolio);
    }

    //-------조회 로직-----------

    @Override
    public PortfolioResponseDTO.PortfolioSearchResponseDTO searchPortfolios(Long portfolioId){

        PostPortfolio existingPortfolio = postPortfolioRepository.findById(portfolioId)
                .orElseThrow(() -> new RuntimeException("Portfolio not found"));

        return PostPortfolioConverter.toPortfolioSearchResponseDTO(existingPortfolio);

    }



    //길게 제목,내용,댓글 수, 좋아요 수 를 반환하는 서비스로직 -> 전체조회 시 이용될 예정
    @Override
    public PortfolioResponseDTO.PortfolioTitleContentDTO portfolioTitleContentDTO(Long portfolioId){

        PostPortfolio postPortfolio = postPortfolioRepository.findById(portfolioId)
                .orElseThrow(() -> new RuntimeException("Community not found"));

        UserEntity userEntity = postPortfolio.getUserEntity();
        String username = userEntity.getUsername();
        Long commentCount = commentRepository.countByPostPortfolioId(portfolioId);

        return PostPortfolioConverter.toportfolioTitleContentDTO(postPortfolio,commentCount,username);

    }

    @Override
    public Page<PortfolioResponseDTO.PortfolioTitleContentDTO> searchAllPortfolios(Pageable pageable) {
        return postPortfolioRepository.findAll(pageable)
                .map(postPortfolio -> {
                    UserEntity userEntity = postPortfolio.getUserEntity();
                    String username = userEntity.getUsername();
                    Long commentCount = commentRepository.countByPostPortfolioId(postPortfolio.getId());
                    return PostPortfolioConverter.toportfolioTitleContentDTO(postPortfolio, commentCount,username);
                });
    }


    @Override
    public void toggleLike(Long portfolioId, String username) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        PostPortfolio postPortfolio = postPortfolioRepository.findById(portfolioId)
                .orElseThrow(() -> new RuntimeException("Community not found"));

        Optional<Likes> existingLike = likeRepository.findByPostPortfolioAndUserEntity(postPortfolio, user);

        if (existingLike.isPresent()) {
            // Remove like
            likeRepository.delete(existingLike.get());
            postPortfolio.removeLike();
        } else {
            // Add like
            likeRepository.save(Likes.builder().postPortfolio(postPortfolio).userEntity(user).build());
            postPortfolio.addLike();
        }

        postPortfolioRepository.save(postPortfolio);
    }




    private static void userValidate(PostPortfolio existingCommunity, UserEntity user) {
        if (!existingCommunity.getUserEntity().equals(user)) {
            throw new RuntimeException("is not not yours");
        }
    }

}
