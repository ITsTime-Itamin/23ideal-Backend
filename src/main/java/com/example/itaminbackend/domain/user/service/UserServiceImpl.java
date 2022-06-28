package com.example.itaminbackend.domain.user.service;

import com.example.itaminbackend.domain.user.constant.UserConstants.EToken;
import com.example.itaminbackend.domain.user.dto.LoginRequest;
import com.example.itaminbackend.domain.user.dto.LoginResponse;
import com.example.itaminbackend.domain.user.entity.User;
import com.example.itaminbackend.domain.user.exception.NotFoundEmailException;
import com.example.itaminbackend.domain.user.repository.UserRepository;
import com.example.itaminbackend.global.config.security.jwt.TokenProvider;
import com.example.itaminbackend.global.dto.TokenInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final RedisTemplate redisTemplate;

    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {
        //System.out.println("로그인테스트1");
        //User user = this.validateEmail(loginRequest.getEmail());
        System.out.println("로그인테스트2");
        TokenInfoResponse tokenInfoResponse = this.validateLogin(loginRequest);
        return LoginResponse.from(tokenInfoResponse);
    }

//    @Override
//    public UserDetails loadUserByUsername(String email){
//        log.info("loadUserByUsername");
//        this.userRepository.findByEmail(email);
//        return this.userRepository.findOneWithAuthoritiesByLoginId(loginId)
//                .map(user -> createUser(loginId, user))
//                .orElseThrow(() -> new UsernameNotFoundException("해당 유저를 찾을 수 없습니다."));
//    }

//    private org.springframework.security.core.userdetails.User createUser(String username, User user) {
//        List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
//                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
//                .collect(Collectors.toList());
//        return new org.springframework.security.core.userdetails.User(user.getLoginId(),
//                user.getPassword(),
//                grantedAuthorities);
//    }

    public TokenInfoResponse validateLogin(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        Authentication authentication = this.authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        TokenInfoResponse tokenInfoResponse = this.tokenProvider.createToken(authentication);
        this.redisTemplate.opsForValue()
                .set(EToken.eRefreshToken.getMessage() + authentication.getName(),
                        tokenInfoResponse.getRefreshToken(), tokenInfoResponse.getRefreshTokenExpirationTime(), TimeUnit.MILLISECONDS);
        return tokenInfoResponse;
    }

    /**
     * validate
     */

    public User validateEmail(String email){
        return this.userRepository.findByEmail(email).orElseThrow(NotFoundEmailException::new);
    }

}
