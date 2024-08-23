package com.example.test1.service.basicProfileService;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.example.test1.Repository.UserRepository;
import com.example.test1.Repository.profileRepository.*;
import com.example.test1.controller.dto.profileDTO.*;
import com.example.test1.converter.*;
import com.example.test1.domain.UserEntity;
import com.example.test1.domain.profile.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BasicProfileServiceImpl implements BasicProfileService {

    private final BasicProfileRepository basicProfileRepository;
    private final UserRepository userRepository;
    private final EducationRepository educationRepository;
    private final ExperienceRepository experienceRepository;
    private final LinkRepository linkRepository;
    private final RewardRepository rewardRepository;
    private final TechRepository techRepository;
    private final CareerRepository careerRepository;


    private final AmazonS3 amazonS3;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;


    @Override
    public BasicProfile save(BasicProfileRequestDTO.BasicProfileSaveRequestDTO request, String username) {

        UserEntity user = getUserEntity(username);

        BasicProfile newBasicProfile = BasicProfileConverter.toBasicProfile(request,user);
        user.setBasicProfile(newBasicProfile);
        return basicProfileRepository.save(newBasicProfile);
    }



    @Override
    public String saveProfileImage(String imagePath, String imageName, String username) {

        UserEntity userEntity = getUserEntity(username);

        BasicProfile findProfile = basicProfileRepository.findByUserEntity(userEntity);
        findProfile.setImageName(imageName);
        findProfile.setImagePath(imagePath);

        basicProfileRepository.save(findProfile);
        return imagePath;

    }





    @Override
    public BasicProfile update(BasicProfileRequestDTO.BasicProfileSaveRequestDTO request, String username,String url){

        UserEntity user = getUserEntity(username);

        BasicProfile byUserEntity = basicProfileRepository.findByUserEntity(user);

        byUserEntity.update(
                request.getName(),
                request.getBirthDate(),
                request.getPhoneNumber(),
                request.getEmail(),
                request.getPhotoName(),
                url
        );

        return basicProfileRepository.save(byUserEntity);
    }

    @Override
    public void delete(String username) {
        UserEntity user = getUserEntity(username);

        // UserEntity에 연관된 Experience 가져오기
        BasicProfile basicProfile = user.getBasicProfile();

        amazonS3.deleteObject(new DeleteObjectRequest(bucket, basicProfile.getImageName()));

        if (basicProfile != null) {
            // UserEntity와 Experience 간의 관계 끊기
            user.removeBasicProfile();
            userRepository.save(user); // 변경된 UserEntity 저장

            // Experience 엔티티 삭제
            basicProfileRepository.delete(basicProfile);
        } else {
            throw new RuntimeException("해당 사용자는 Experience가 없습니다.");
        }
    }

    @Override
    public BasicProfileResponseDTO.BasicProfileGetDTO getBasicProfile(String username){

        UserEntity user = getUserEntity(username);

        BasicProfile basicProfile = user.getBasicProfile();
        return BasicProfileConverter.toBasicProfileGetDTO(basicProfile);
    }


    @Override
    public BasicProfileResponseDTO.toShowAllProfile showAllProfile(String username){

        UserEntity user = getUserEntity(username);


        BasicProfile findBasicProfile = basicProfileRepository.findByUserEntity(user);
        Education findEducation = educationRepository.findByUserEntity(user);
        Experience findExperience = experienceRepository.findByUserEntity(user);
        Link findLink = linkRepository.findByUserEntity(user);
        Reward findReward = rewardRepository.findByUserEntity(user);
        Tech findTech = techRepository.findByUserEntity(user);
        Career career=careerRepository.findByUserEntity(user);


        BasicProfileResponseDTO.BasicProfileGetDTO basicProfileGetDTO = BasicProfileConverter.toBasicProfileGetDTO(findBasicProfile);
        EducationRequestDTO.EducationGetResponseDTO responseDTO = EducationConverter.toResponseDTO(findEducation);
        ExperienceDTO.ExperienceResponseDTO experienceResponseDTO = ExperienceConverter.toExperienceResponseDTO(findExperience);
        linkDTO.LinkResponseDTO linkResponseDTO = LinkConverter.toLinkResponseDTO(findLink);
        RewardDTO.RewardResponseDTO response = RewardConverter.toResponse(findReward);
        TechDTO.TechResponseDTO responseDTO1 = TechConverter.toResponseDTO(findTech);

        CareerDTO.ResponseDTO responseDTO2=CareerConverter.toResponseDTO(career);

        return BasicProfileConverter.toShowAllProfile(basicProfileGetDTO,responseDTO,experienceResponseDTO,linkResponseDTO,response,responseDTO1,responseDTO2);



    }




    private UserEntity getUserEntity(String username) {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(
                ()-> new RuntimeException("회원을 찾을 수 없습니다")
        );
        return user;
    }

}