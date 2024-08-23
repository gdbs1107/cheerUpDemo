package com.example.test1.service.userService;

import com.example.test1.Repository.RefreshRepository;
import com.example.test1.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class userCommandServiceImpl implements UserCommandService {

    private final JwtUtil jwtUtil;
    private final RefreshRepository refreshRepository;

    @Override
    public void logout(String refreshToken) {
        if (refreshToken == null) {
            throw new RuntimeException("유효하지 않은 토큰입니다");
        }

        try {
            jwtUtil.isExpired(refreshToken);
        } catch (Exception e) {
            throw new RuntimeException("refresh expired token");
        }

        String category = jwtUtil.getCategory(refreshToken);

        if (!category.equals("refresh")) {
            throw new RuntimeException("refresh Token null");
        }

        String username = jwtUtil.getUsername(refreshToken);

        refreshRepository.deleteByRefresh(refreshToken);
    }
}
