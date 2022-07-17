package com.example.itaminbackend.domain.user.service;

import com.example.itaminbackend.domain.user.dto.LoginRequest;
import com.example.itaminbackend.domain.user.dto.LoginResponse;
import com.example.itaminbackend.domain.user.dto.UserDto;
import com.example.itaminbackend.domain.user.dto.UserDto.GoogleLoginRequest;
import com.example.itaminbackend.domain.user.dto.UserDto.NaverLoginRequest;
import com.example.itaminbackend.domain.user.entity.User;

import java.io.IOException;

public interface UserService {

    LoginResponse loginUser(LoginRequest loginRequest);
    User validateEmail(String email);

    LoginResponse authGoogleUser(GoogleLoginRequest googleLoginRequest);
    LoginResponse authNaverUser(NaverLoginRequest naverLoginRequest) throws IOException;

    User saveOrUpdate(User user);
   // GoogleLoginRequest providegoogleJWTToken(User user);
//
//    UserDto.NaverLoginRequest providenaverJWTToken(User user);
}
