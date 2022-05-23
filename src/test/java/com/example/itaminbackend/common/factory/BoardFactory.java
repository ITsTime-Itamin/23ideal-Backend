package com.example.itaminbackend.common.factory;

import com.example.itaminbackend.domain.board.constant.BoardConstants;
import com.example.itaminbackend.domain.board.dto.BoardDto;
import com.example.itaminbackend.domain.board.entity.Board;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.example.itaminbackend.domain.board.constant.BoardConstants.*;
import static com.example.itaminbackend.domain.board.dto.BoardDto.*;

public class BoardFactory {

    private BoardFactory() {
    }

    public static class Builder {

    }

    public static List<CreateRequest> mockCreateRequests() {
        List<MultipartFile> images = List.of(FileFactory.getTestImage1(), FileFactory.getTestImage2());
        CreateRequest fixture1 = CreateRequest.builder()
                .title("게시판1")
                .content("자유게시판")
                .boardType(EBoardType.eFreeBoard)
                .files(images)
                .build();

        CreateRequest fixture2 = CreateRequest.builder()
                .title("게시판2")
                .content("취업공고")
                .boardType(EBoardType.eJobPosting)
                .files(images)
                .build();
        return List.of(fixture1, fixture2);
    }
}
