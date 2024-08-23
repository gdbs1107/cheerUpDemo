package com.example.test1.service.linkRepository;

import com.example.test1.Repository.UserRepository;
import com.example.test1.Repository.profileRepository.LinkRepository;
import com.example.test1.controller.dto.profileDTO.linkDTO;
import com.example.test1.converter.LinkConverter;
import com.example.test1.domain.UserEntity;
import com.example.test1.domain.profile.Link;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LinkServiceImpl implements LinkService {

    private final LinkRepository linkRepository;
    private final UserRepository userRepository;

    @Override
    public void save(linkDTO.LinkRequestDTO request, String username){

        UserEntity userEntity = findUserEntity(username);
        Link link = LinkConverter.toConvert(request, userEntity);
        linkRepository.save(link);
    }

    @Override
    public void update(linkDTO.LinkRequestDTO request, String username){
        UserEntity userEntity = findUserEntity(username);
        Link byUserEntity = linkRepository.findByUserEntity(userEntity);

        byUserEntity.update(
                request.getAddress()
        );

        linkRepository.save(byUserEntity);
    }

    @Override
    public void delete(String username) {
        UserEntity user = findUserEntity(username);

        // UserEntity에 연관된 Experience 가져오기
        Link link = user.getLink();

        if (link != null) {
            // UserEntity와 Experience 간의 관계 끊기
            user.removeLink();
            userRepository.save(user); // 변경된 UserEntity 저장

            // Experience 엔티티 삭제
            linkRepository.delete(link);
        } else {
            throw new RuntimeException("해당 사용자는 Experience가 없습니다.");
        }
    }


    @Override
    public linkDTO.LinkResponseDTO getLink(String username){
        UserEntity userEntity = findUserEntity(username);
        Link link=linkRepository.findByUserEntity(userEntity);

        return LinkConverter.toLinkResponseDTO(link);
    }




    private UserEntity findUserEntity(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new RuntimeException("회원을 찾을 수 없습니다")
        );
    }
}
