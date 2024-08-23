package com.example.test1.service.postPortfolioService;

import com.example.test1.controller.dto.postDTO.PortfolioResponseDTO;
import com.example.test1.controller.dto.postDTO.PostPortfolioRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface PostPortfolioService {
    void postPortfolio(PostPortfolioRequestDTO.postPortfolioRequestDTO request, String username);

    void updatePortfolio(Long portfolioId, PostPortfolioRequestDTO.postPortfolioRequestDTO request, String username);

    void deletePortfolio(Long portfolioId, String username);

    PortfolioResponseDTO.PortfolioSearchResponseDTO searchPortfolios(Long portfolioId);

    //길게 제목,내용,댓글 수, 좋아요 수 를 반환하는 서비스로직 -> 전체조회 시 이용될 예정
    PortfolioResponseDTO.PortfolioTitleContentDTO portfolioTitleContentDTO(Long portfolioId);

    Page<PortfolioResponseDTO.PortfolioTitleContentDTO> searchAllPortfolios(Pageable pageable);

    void toggleLike(Long portfolioId, String username);
}
