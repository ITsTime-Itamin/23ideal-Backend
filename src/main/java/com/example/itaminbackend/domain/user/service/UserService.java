package com.example.itaminbackend.domain.user.service;

import com.example.itaminbackend.domain.user.dto.LoginRequest;
import com.example.itaminbackend.domain.user.dto.LoginResponse;
import com.example.itaminbackend.domain.user.entity.User;

public interface UserService {

    LoginResponse loginUser(LoginRequest loginRequest);
    User validateEmail(String email);
}
