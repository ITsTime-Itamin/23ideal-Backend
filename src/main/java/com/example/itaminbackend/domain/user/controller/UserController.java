package com.example.itaminbackend.domain.user.controller;

import com.example.itaminbackend.domain.board.dto.BoardDto;
import com.example.itaminbackend.domain.user.constant.UserConstants;
import com.example.itaminbackend.domain.user.constant.UserConstants.EUserResponseMessage;
import com.example.itaminbackend.domain.user.dto.LoginRequest;
import com.example.itaminbackend.domain.user.dto.LoginResponse;
import com.example.itaminbackend.domain.user.service.UserService;
import com.example.itaminbackend.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/testLogin")
    public ResponseEntity<ResponseDto<LoginResponse>> testLogin(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = this.userService.loginUser(loginRequest);
        return ResponseEntity.ok(ResponseDto.create(EUserResponseMessage.LOGIN_SUCCESS.getMessage(), loginResponse));
    }

}
