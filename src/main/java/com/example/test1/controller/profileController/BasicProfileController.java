package com.example.test1.controller.profileController;

import com.example.test1.Repository.profileRepository.BasicProfileRepository;
import com.example.test1.apiPayload.ApiResponse;
import com.example.test1.controller.dto.ImageUploadDTO;
import com.example.test1.controller.dto.profileDTO.BasicProfileRequestDTO;
import com.example.test1.controller.dto.profileDTO.BasicProfileResponseDTO;
import com.example.test1.service.S3Service;
import com.example.test1.service.basicProfileService.BasicProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/basicProfile")
@RequiredArgsConstructor
@Slf4j
public class BasicProfileController {

    private final BasicProfileService basicProfileService;
    private final S3Service s3Service;


    @PostMapping(path = "/")
    public ApiResponse<String> saveBasicProfile(@RequestBody BasicProfileRequestDTO.BasicProfileSaveRequestDTO request,
                                                @AuthenticationPrincipal UserDetails userDetails) {


        basicProfileService.save(request, userDetails.getUsername());
        return ApiResponse.onSuccess("save success");
    }



    @PostMapping(path = "/images",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<String> saveBasicProfilePhoto(@RequestPart(value = "file", required = false) MultipartFile multipartFile,
                                                     @RequestPart String imageName,
                                                     @AuthenticationPrincipal UserDetails userDetails
                                                     ) throws IOException {

        String extend = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        ImageUploadDTO result = s3Service.upload(imageName, multipartFile, extend);
        log.info("url:{}", result.getUrl());

        String imagePath = basicProfileService.saveProfileImage(result.getUrl(), result.getNewFileName(), userDetails.getUsername());

        return ApiResponse.onSuccess(imagePath);
    }




    //이거 업데이트 메서드 이상한거임 수정 필요
    @PutMapping(path = "/",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<String> updateBasicProfile(@Valid @RequestBody BasicProfileRequestDTO.BasicProfileSaveRequestDTO request,
                                                  @AuthenticationPrincipal UserDetails userDetails,
                                                  @RequestPart(value = "file", required = false) MultipartFile multipartFile) throws IOException {

        String extend = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        ImageUploadDTO result = s3Service.upload(request.getPhotoName(), multipartFile, extend);
        log.info("url:{}", result.getUrl());

        basicProfileService.update(request,userDetails.getUsername(), result.getUrl());
        return ApiResponse.onSuccess("update success");
    }



    @DeleteMapping("/")
    public ApiResponse<String> deleteBasicProfile(@AuthenticationPrincipal UserDetails userDetails){

        basicProfileService.delete(userDetails.getUsername());
        return ApiResponse.onSuccess("delete success");
    }

    @GetMapping("/")
    public ApiResponse<BasicProfileResponseDTO.BasicProfileGetDTO> getBasicProfile(@AuthenticationPrincipal UserDetails userDetails){
        return ApiResponse.onSuccess(basicProfileService.getBasicProfile(userDetails.getUsername()));
    }

    @GetMapping("/all")
    private ApiResponse<BasicProfileResponseDTO.toShowAllProfile> getAllProfile(@AuthenticationPrincipal UserDetails userDetails){
        BasicProfileResponseDTO.toShowAllProfile result = basicProfileService.showAllProfile(userDetails.getUsername());
        return ApiResponse.onSuccess(result);
    }
}