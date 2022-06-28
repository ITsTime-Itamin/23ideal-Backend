package com.example.itaminbackend.domain.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "로그인을 위한 요청 객체")
public class LoginRequest {

    @NotBlank(message = "회원의 로그인Id를 입력해주세요.")
    @ApiModelProperty(notes = "로그인 Id를 입력해 주세요.")
    private String email;

    @NotBlank(message = "회원의 비밀번호를 입력해 주세요.")
    @ApiModelProperty(notes = "회원의 비밀번호를 입력해 주세요.")
    private String password;

}
