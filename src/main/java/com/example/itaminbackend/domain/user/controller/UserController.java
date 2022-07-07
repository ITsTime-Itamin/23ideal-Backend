package com.example.itaminbackend.domain.user.controller;

import com.example.itaminbackend.domain.user.constant.UserConstants;
import com.example.itaminbackend.domain.user.constant.UserConstants.EUserResponseMessage;
import com.example.itaminbackend.domain.user.dto.LoginRequest;
import com.example.itaminbackend.domain.user.dto.LoginResponse;
import com.example.itaminbackend.domain.user.dto.UserDto;
import com.example.itaminbackend.domain.user.dto.UserDto.GoogleLoginRequest;
import com.example.itaminbackend.domain.user.dto.UserDto.NaverLoginRequest;
import com.example.itaminbackend.domain.user.service.UserService;
import com.example.itaminbackend.global.dto.ResponseDto;
import com.example.itaminbackend.global.dto.TokenInfoResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "구글 로그인", notes = "구글 로그인을 합니다.")
    @PostMapping("/googleLogin")
    public ResponseEntity<ResponseDto<GoogleLoginRequest>> googleLogin(@Valid @RequestBody GoogleLoginRequest googleLoginRequest){
        return ResponseEntity.ok(ResponseDto.create(EUserResponseMessage.GOOGLELOGIN_SUCCESS.getMessage(), this.userService.authGoogleUser(googleLoginRequest)));
    }

    @ApiOperation(value = "네이버 로그인", notes = "네이버 로그인을 합니다.")
    @PostMapping("/naverLogin")
    public ResponseEntity<ResponseDto<LoginResponse>> naverLogin(@Valid @RequestBody NaverLoginRequest naverLoginRequest) throws IOException {
        return ResponseEntity.ok(ResponseDto.create(EUserResponseMessage.NAVERLOGIN_SUCCES.getMessage(), this.userService.authNaverUser(naverLoginRequest)));
    }

    @PostMapping("/testLogin")
    public ResponseEntity<ResponseDto<LoginResponse>> testLogin(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = this.userService.loginUser(loginRequest);
        return ResponseEntity.ok(ResponseDto.create(EUserResponseMessage.LOGIN_SUCCESS.getMessage(), loginResponse));
    }

}
