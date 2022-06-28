package com.example.itaminbackend.domain.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

public class UserDto {

    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "구글로그인을 위한 요청 객체")
    @NoArgsConstructor
    public static class GoogleLoginRequest {
        @NotBlank(message = "인증 jwt 토큰")
        @ApiModelProperty(notes = "구글 idToken을 주세요.")
        private String token;

        public void setToken(String token) {
            this.token = token;
        }
    }
    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "네이버로그인을 위한 요청 객체")
    @NoArgsConstructor
    public static class NaverLoginRequest {
        @NotBlank(message = "인증 jwt 토큰")
        @ApiModelProperty(notes = "네이버 accessToken을 주세요.")
        private String token;

        public void setToken(String token) {
            this.token = token;
        }
    }
}
