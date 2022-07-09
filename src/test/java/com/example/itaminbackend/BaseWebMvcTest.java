package com.example.itaminbackend;

import com.example.itaminbackend.domain.board.controller.BoardController;
import com.example.itaminbackend.domain.board.service.BoardService;
import com.example.itaminbackend.global.config.security.jwt.JwtAccessDeniedHandler;
import com.example.itaminbackend.global.config.security.jwt.JwtAuthenticationEntryPoint;
import com.example.itaminbackend.global.config.security.jwt.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest({
        BoardController.class
})
public abstract class BaseWebMvcTest extends BaseTest{

    @MockBean
    protected BoardService boardService;

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    protected TokenProvider tokenProvider;

    @MockBean
    protected JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @MockBean
    protected JwtAccessDeniedHandler jwtAccessDeniedHandler;
}
