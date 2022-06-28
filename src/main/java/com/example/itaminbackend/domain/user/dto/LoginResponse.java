package com.example.itaminbackend.domain.user.dto;

import com.example.itaminbackend.global.dto.TokenInfoResponse;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "로그인을 위한 응답 객체")
public class LoginResponse {

    private String accessToken;
    private String refreshToken;

    public static LoginResponse from(TokenInfoResponse tokenInfoResponse) {
        return LoginResponse.builder()
                .accessToken(tokenInfoResponse.getAccessToken())
                .refreshToken(tokenInfoResponse.getRefreshToken())
                .build();
    }

}
