package com.example.itaminbackend.global.config.security.jwt;

import org.json.simple.JSONObject;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

import static com.example.itaminbackend.global.constant.GlobalConstants.*;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        String exception = (String)request.getAttribute("exception");
        if(exception == null) setResponse(response, JWTExceptionList.UNKNOWN_ERROR);
        //잘못된 타입의 토큰인 경우
        else if(exception.equals(JWTExceptionList.WRONG_TYPE_TOKEN.getErrorCode())) setResponse(response, JWTExceptionList.WRONG_TYPE_TOKEN);
        //토큰 만료된 경우
        else if(exception.equals(JWTExceptionList.EXPIRED_TOKEN.getErrorCode())) setResponse(response, JWTExceptionList.EXPIRED_TOKEN);
        //지원되지 않는 토큰인 경우
        else if(exception.equals(JWTExceptionList.UNSUPPORTED_TOKEN.getErrorCode())) setResponse(response, JWTExceptionList.UNSUPPORTED_TOKEN);
        else setResponse(response, JWTExceptionList.ACCESS_DENIED);

    }

    private void setResponse(HttpServletResponse response, JWTExceptionList exceptionCode) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        JSONObject responseJson = new JSONObject();
        responseJson.put("message", exceptionCode.getMessage());
        responseJson.put("errorCode", exceptionCode.getErrorCode());

        response.getWriter().print(responseJson);
    }
}