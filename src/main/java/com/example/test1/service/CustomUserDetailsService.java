package com.example.test1.service;


import com.example.test1.Repository.UserRepository;
import com.example.test1.controller.dto.CustomUserDetails;
import com.example.test1.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity =userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        if(userEntity!=null){
            return new CustomUserDetails(userEntity);
        }


        return null;
    }
}
