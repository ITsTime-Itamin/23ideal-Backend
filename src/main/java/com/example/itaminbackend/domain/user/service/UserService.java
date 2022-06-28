package com.example.itaminbackend.domain.user.service;

import com.example.itaminbackend.domain.user.dto.LoginRequest;
import com.example.itaminbackend.domain.user.dto.LoginResponse;
import com.example.itaminbackend.domain.user.dto.UserDto;
import com.example.itaminbackend.domain.user.entity.User;

import java.io.IOException;

public interface UserService {

    LoginResponse loginUser(LoginRequest loginRequest);
    User validateEmail(String email);

    UserDto.GoogleLoginRequest authGoogleUser(UserDto.GoogleLoginRequest googleLoginRequest);
    UserDto.NaverLoginRequest authNaverUser(UserDto.NaverLoginRequest naverLoginRequest) throws IOException;

    User saveOrUpdate(User user);
    UserDto.GoogleLoginRequest providegoogleJWTToken(User user);

    UserDto.NaverLoginRequest providenaverJWTToken(User user);
}
