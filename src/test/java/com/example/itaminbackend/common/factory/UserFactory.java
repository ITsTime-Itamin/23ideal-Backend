package com.example.itaminbackend.common.factory;

import com.example.itaminbackend.domain.board.constant.BoardConstants;
import com.example.itaminbackend.domain.board.dto.BoardDto;
import com.example.itaminbackend.domain.user.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class UserFactory {

    private UserFactory() {
    }

    public static class Builder {

    }

    public static List<User> mockUsers(){
        User fixture1 = User.builder()
                .userId(2000L)
                .name("정우")
                .email("rlawjddn5980@naver.com")
                .build();

        User fixture2 = User.builder()
                .userId(2001L)
                .name("민수")
                .email("alstn5980@naver.com")
                .build();

        return List.of(fixture1, fixture2);
    }
}
