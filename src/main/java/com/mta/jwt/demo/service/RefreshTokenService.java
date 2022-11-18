package com.mta.jwt.demo.service;

import com.mta.jwt.demo.entity.RefreshToken;
import com.mta.jwt.demo.entity.User;
import com.mta.jwt.demo.exception.TokenRefreshException;
import com.mta.jwt.demo.repository.RefreshTokenRepository;
import com.mta.jwt.demo.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class RefreshTokenService {
    @Value("${app.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;

    @Value("${app.jwtRefreshSecret}")
    private String jwtRefreshSecret;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(String userId) {
        RefreshToken refreshToken = new RefreshToken();

        User user = userRepository.findById(userId).get();

        refreshToken.setUser(user);
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        //refreshToken.setToken(UUID.randomUUID().toString());

        Map<String,Object> claim = new HashMap<>();
        claim.put("a","b");
        claim.put("a2","b2");
        claim.put("a3","b3");

        refreshToken.setToken(
                Jwts.builder()
                        .setSubject(user.getUsername())
                        .setClaims(claim)
                        .setIssuedAt(new Date())
                        .setExpiration(new Date((new Date()).getTime() + refreshTokenDurationMs))
                        .signWith(SignatureAlgorithm.HS512, jwtRefreshSecret)
                        .compact()
        );

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }
        return token;
    }

    @Transactional
    public int deleteByUserId(String userId) {
        return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
    }
}
