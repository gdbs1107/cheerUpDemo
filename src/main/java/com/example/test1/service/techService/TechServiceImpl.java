package com.example.test1.service.techService;

import com.example.test1.Repository.UserRepository;
import com.example.test1.Repository.profileRepository.TechRepository;
import com.example.test1.controller.dto.profileDTO.TechDTO;
import com.example.test1.converter.TechConverter;
import com.example.test1.domain.UserEntity;
import com.example.test1.domain.profile.Tech;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TechServiceImpl implements TechService {

    private final TechRepository techRepository;
    private final UserRepository userRepository;

    @Override
    public void save(TechDTO.TechRequestDTO request, String username){

        UserEntity userEntity = findUserEntity(username);
        Tech tech = TechConverter.toTech(request, userEntity);
        techRepository.save(tech);
    }

    @Override
    public void update(TechDTO.TechRequestDTO request, String username){
        UserEntity userEntity = findUserEntity(username);
        Tech byUserEntity = techRepository.findByUserEntity(userEntity);

        byUserEntity.update(
                request.getTechName(),
                TechConverter.toTechLevel(request.getTechLevel())
        );

        techRepository.save(byUserEntity);
    }

    @Override
    public void delete(String username) {
        UserEntity user = findUserEntity(username);

        // UserEntity에 연관된 Experience 가져오기
        Tech tech=user.getTech();

        if (tech != null) {
            // UserEntity와 Experience 간의 관계 끊기
            user.removeTech();
            userRepository.save(user); // 변경된 UserEntity 저장

            // Experience 엔티티 삭제
            techRepository.delete(tech);
        } else {
            throw new RuntimeException("해당 사용자는 tech가 없습니다.");
        }
    }


    @Override
    public TechDTO.TechResponseDTO getTech(String username){
        UserEntity userEntity = findUserEntity(username);
        Tech tech=techRepository.findByUserEntity(userEntity);

        return TechConverter.toResponseDTO(tech);
    }




    private UserEntity findUserEntity(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new RuntimeException("회원을 찾을 수 없습니다")
        );
    }
}
