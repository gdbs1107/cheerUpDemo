package com.example.test1.service.educationService;

import com.example.test1.Repository.UserRepository;
import com.example.test1.Repository.profileRepository.EducationRepository;
import com.example.test1.controller.dto.profileDTO.EducationRequestDTO;
import com.example.test1.converter.EducationConverter;
import com.example.test1.domain.UserEntity;
import com.example.test1.domain.profile.Education;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EducationServiceImpl implements EducationService {

    private final EducationRepository educationRepository;
    private final UserRepository userRepository;


    @Override
    public void save(EducationRequestDTO.EducationSaveRequestDTO request, String username) {

        UserEntity user = userRepository.findByUsername(username).orElseThrow(
                () -> new RuntimeException("회원을 찾을 수 없습니다")
        );

        Education education = EducationConverter.toEducation(request, user);
        educationRepository.save(education);

    }


    @Override
    public void update(EducationRequestDTO.EducationSaveRequestDTO request, String username){

        UserEntity user = userRepository.findByUsername(username).orElseThrow(
                () -> new RuntimeException("회원을 찾을 수 없습니다")
        );

        Education byUserEntity = educationRepository.findByUserEntity(user);

        byUserEntity.update(
                request.getDegree(),
                request.getMajor(),
                request.getSchoolName(),
                request.getStartSchool(),
                request.getEndSchool(),
                request.getMajorName());

        educationRepository.save(byUserEntity);
    }




    @Override
    public void delete(String username) {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(
                () -> new RuntimeException("회원을 찾을 수 없습니다")
        );

        // Education 삭제 전에 연결 끊기
        if (user.getEducation() != null) {
            user.removeEducation();
            userRepository.save(user);
        }

        Education education = educationRepository.findByUserEntity(user);
        if (education != null) {
            educationRepository.delete(education);
        }
    }



    @Override
    public EducationRequestDTO.EducationGetResponseDTO getEducation(String username){

        UserEntity user = userRepository.findByUsername(username).orElseThrow(
                () -> new RuntimeException("회원을 찾을 수 없습니다")
        );

        Education byUserEntity = educationRepository.findByUserEntity(user);

        return EducationConverter.toResponseDTO(byUserEntity);

    }
}
