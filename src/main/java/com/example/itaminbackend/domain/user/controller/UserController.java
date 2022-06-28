package com.example.itaminbackend.domain.user.controller;

import com.example.itaminbackend.domain.user.constant.UserConstants;
import com.example.itaminbackend.domain.user.dto.UserDto;
import com.example.itaminbackend.domain.user.service.UserService;
import com.example.itaminbackend.global.dto.ResponseDto;
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
    public ResponseEntity<ResponseDto<UserDto.GoogleLoginRequest>> googleLogin(@Valid @ModelAttribute UserDto.GoogleLoginRequest googleLoginRequest){
        return ResponseEntity.ok(ResponseDto.create(UserConstants.EBoardResponseMessage.GOOGLELOGIN_SUCCESS.getMessage(), this.userService.authGoogleUser(googleLoginRequest)));
    }

    @ApiOperation(value = "네이버 로그인", notes = "네이버 로그인을 합니다.")
    @PostMapping("/naverLogin")
    public ResponseEntity<ResponseDto<UserDto.NaverLoginRequest>> naverLogin(@Valid @ModelAttribute UserDto.NaverLoginRequest naverLoginRequest) throws IOException {
        return ResponseEntity.ok(ResponseDto.create(UserConstants.EBoardResponseMessage.NAVERLOGIN_SUCCES.getMessage(), this.userService.authNaverUser(naverLoginRequest)));
    }

}
