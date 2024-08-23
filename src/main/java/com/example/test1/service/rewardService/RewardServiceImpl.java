package com.example.test1.service.rewardService;

import com.example.test1.Repository.UserRepository;
import com.example.test1.Repository.profileRepository.RewardRepository;
import com.example.test1.controller.dto.profileDTO.RewardDTO;
import com.example.test1.converter.RewardConverter;
import com.example.test1.domain.UserEntity;
import com.example.test1.domain.profile.Reward;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RewardServiceImpl implements RewardService {

    private final RewardRepository rewardRepository;
    private final UserRepository userRepository;

    @Override
    public void save(RewardDTO.RewardRequestDTO request, String username){

        UserEntity userEntity = findUserEntity(username);
        Reward reward = RewardConverter.toReward(request, userEntity);
        rewardRepository.save(reward);
    }

    @Override
    public void update(RewardDTO.RewardRequestDTO request, String username){
        UserEntity userEntity = findUserEntity(username);
        Reward byUserEntity = rewardRepository.findByUserEntity(userEntity);

        byUserEntity.update(
                request.getRewardName(),
                request.getPublisher(),
                request.getPublishDate(),
                request.getAuthentication()
        );

        rewardRepository.save(byUserEntity);
    }

    @Override
    public void delete(String username) {
        UserEntity user = findUserEntity(username);

        // UserEntity에 연관된 Experience 가져오기
        Reward reward=user.getReward();

        if (reward != null) {
            // UserEntity와 Experience 간의 관계 끊기
            user.removeReward();
            userRepository.save(user); // 변경된 UserEntity 저장

            // Experience 엔티티 삭제
            rewardRepository.delete(reward);
        } else {
            throw new RuntimeException("해당 사용자는 reward가 없습니다.");
        }
    }


    @Override
    public RewardDTO.RewardResponseDTO getReward(String username){
        UserEntity userEntity = findUserEntity(username);
        Reward reward=rewardRepository.findByUserEntity(userEntity);

        return RewardConverter.toResponse(reward);
    }




    private UserEntity findUserEntity(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new RuntimeException("회원을 찾을 수 없습니다")
        );
    }
}
