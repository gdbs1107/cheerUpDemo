package com.example.test1.controller.postContoller;

import com.example.test1.apiPayload.ApiResponse;
import com.example.test1.controller.dto.postDTO.PortfolioResponseDTO;
import com.example.test1.controller.dto.postDTO.PostPortfolioRequestDTO;
import com.example.test1.service.postPortfolioService.PostPortfolioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/postPortfolios")
public class PostPortfolioController {

    private final PostPortfolioService postPortfolioService;

    @PostMapping("/")
    public ApiResponse<String> postPortfolio(@RequestBody PostPortfolioRequestDTO.postPortfolioRequestDTO request,
                                             @AuthenticationPrincipal UserDetails userDetails) {

        postPortfolioService.postPortfolio(request,userDetails.getUsername());
        return ApiResponse.onSuccess("save");
    }


    @PutMapping("/{portfolioId}")
    public ApiResponse<String> updatePortfolio(@PathVariable Long portfolioId,
                                               @RequestBody PostPortfolioRequestDTO.postPortfolioRequestDTO request,
                                               @AuthenticationPrincipal UserDetails userDetails ){

        postPortfolioService.updatePortfolio(portfolioId,request,userDetails.getUsername());

        return ApiResponse.onSuccess("updated");
    }

    @DeleteMapping("/{portfolioId}")
    public ApiResponse<String> deleterPortfolioId(@PathVariable Long portfolioId,
                                                @AuthenticationPrincipal UserDetails userDetails){

        postPortfolioService.deletePortfolio(portfolioId,userDetails.getUsername());
        return ApiResponse.onSuccess("deleted");
    }



    //조회 로직들
    @GetMapping("/{portfolioId}")
    public ApiResponse<PortfolioResponseDTO.PortfolioSearchResponseDTO> searchCommunity(@PathVariable Long portfolioId){

        PortfolioResponseDTO.PortfolioSearchResponseDTO findPortfolio = postPortfolioService.searchPortfolios(portfolioId);
        return ApiResponse.onSuccess(findPortfolio);

    }

    @GetMapping("/")
    public ApiResponse<PortfolioResponseDTO.PortfolioPageResponseDTO> searchAllPortfolios(
            @Valid
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<PortfolioResponseDTO.PortfolioTitleContentDTO> portfolioTitlePages = postPortfolioService.searchAllPortfolios(PageRequest.of(page, size));

        PortfolioResponseDTO.PortfolioPageResponseDTO response = new PortfolioResponseDTO.PortfolioPageResponseDTO(
                portfolioTitlePages.getTotalPages(),
                portfolioTitlePages.getTotalElements(),
                portfolioTitlePages.getContent()
        );

        return ApiResponse.onSuccess(response);
    }

    @PostMapping("/{portfolioId}/like")
    public ApiResponse<String> toggleLike(@PathVariable Long portfolioId,
                                          @AuthenticationPrincipal UserDetails userDetails) {
        postPortfolioService.toggleLike(portfolioId, userDetails.getUsername());
        return ApiResponse.onSuccess("Like toggled");
    }
}
