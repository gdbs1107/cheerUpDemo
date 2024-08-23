package com.example.test1.service.careerService;

import com.example.test1.Repository.UserRepository;
import com.example.test1.Repository.profileRepository.CareerRepository;
import com.example.test1.controller.dto.profileDTO.CareerDTO;
import com.example.test1.converter.CareerConverter;
import com.example.test1.domain.UserEntity;
import com.example.test1.domain.profile.Career;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CareerServiceImpl implements CareerService {

    private final CareerRepository careerRepository;
    private final UserRepository userRepository;

    @Override
    public void save(CareerDTO.CareerRequestDTO request, String username){

        UserEntity userEntity = findUserEntity(username);
        Career career = CareerConverter.toConvert(request,userEntity);
        careerRepository.save(career);
    }

    @Override
    public void update(CareerDTO.CareerRequestDTO request, String username){
        UserEntity userEntity = findUserEntity(username);
        Career byUserEntity = careerRepository.findByUserEntity(userEntity);

        byUserEntity.update(
                request.getCompanyName(),
                request.getStartComp(),
                request.getEndComp(),
                request.getPosition(),
                request.getCompanyFunction(),
                request.getDuties()
        );

        careerRepository.save(byUserEntity);
    }

    @Override
    public void delete(String username) {
        UserEntity user = findUserEntity(username);

        // UserEntity에 연관된 Experience 가져오기
        Career career = user.getCareer();

        if (career != null) {
            // UserEntity와 Experience 간의 관계 끊기
            user.removeCareer();
            userRepository.save(user); // 변경된 UserEntity 저장

            // Experience 엔티티 삭제
            careerRepository.delete(career);
        } else {
            throw new RuntimeException("해당 사용자는 Career가 없습니다.");
        }
    }


    @Override
    public CareerDTO.ResponseDTO getCareer(String username){
        UserEntity userEntity = findUserEntity(username);
        Career career=careerRepository.findByUserEntity(userEntity);

        return CareerConverter.toResponseDTO(career);
    }




    private UserEntity findUserEntity(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new RuntimeException("회원을 찾을 수 없습니다")
        );
    }
}
