package com.example.test1.service.experienceService;

import com.example.test1.Repository.UserRepository;
import com.example.test1.Repository.profileRepository.ExperienceRepository;
import com.example.test1.controller.dto.profileDTO.ExperienceDTO;
import com.example.test1.converter.ExperienceConverter;
import com.example.test1.domain.UserEntity;
import com.example.test1.domain.profile.Experience;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ExperienceServiceImpl implements ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final UserRepository userRepository;

    @Override
    public void save(ExperienceDTO.ExperienceRequestDTO request, String username){

        UserEntity userEntity = findUserEntity(username);
        Experience experience = ExperienceConverter.toExperience(request, userEntity);
        experienceRepository.save(experience);
    }

    @Override
    public void update(ExperienceDTO.ExperienceRequestDTO request, String username){
        UserEntity userEntity = findUserEntity(username);
        Experience byUserEntity = experienceRepository.findByUserEntity(userEntity);

        byUserEntity.update(
                ExperienceConverter.toExperienceCategory(request.getExperienceCategory()),
                request.getExpName(),
                request.getTopicExp(),
                request.getContentExp(),
                request.getLinkExp(),
                request.getStartExp(),
                request.getEndExp()
        );

        experienceRepository.save(byUserEntity);
    }

    @Override
    public void delete(String username) {
        UserEntity user = findUserEntity(username);

        // UserEntity에 연관된 Experience 가져오기
        Experience experience = user.getExperience();

        if (experience != null) {
            // UserEntity와 Experience 간의 관계 끊기
            user.removeExperience();
            userRepository.save(user); // 변경된 UserEntity 저장

            // Experience 엔티티 삭제
            experienceRepository.delete(experience);
        } else {
            throw new RuntimeException("해당 사용자는 Experience가 없습니다.");
        }
    }


    @Override
    public ExperienceDTO.ExperienceResponseDTO getExperience(String username){
        UserEntity userEntity = findUserEntity(username);
        Experience experience=experienceRepository.findByUserEntity(userEntity);

        return ExperienceConverter.toExperienceResponseDTO(experience);
    }




    private UserEntity findUserEntity(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new RuntimeException("회원을 찾을 수 없습니다")
        );
    }
}
