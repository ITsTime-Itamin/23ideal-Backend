package com.example.itaminbackend.common.factory;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.example.itaminbackend.domain.board.constant.BoardConstants.EBoardType;
import static com.example.itaminbackend.domain.board.dto.BoardDto.CreateRequest;
import static com.example.itaminbackend.domain.board.dto.BoardDto.UpdateRequest;

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
                .boardType(EBoardType.FREE.name())
                .files(images)
                .build();

        CreateRequest fixture2 = CreateRequest.builder()
                .title("게시판2")
                .content("취업공고")
                .boardType(EBoardType.FREE.name())
                .files(images)
                .build();
        return List.of(fixture1, fixture2);
    }

    public static List<UpdateRequest> mockUpdateRequests() {
        List<MultipartFile> images = List.of(FileFactory.getTestImage1(), FileFactory.getTestImage2());
        UpdateRequest fixture1 = UpdateRequest.builder()
                .boardId("101")
                .title("변경된게시판1")
                .content("변경된취업공고")
                .boardType(EBoardType.JOB.name())
                .files(images)
                .build();

        UpdateRequest fixture2 = UpdateRequest.builder()
                .boardId("102")
                .title("게시판2")
                .content("변경된자유게시판")
                .boardType(EBoardType.FREE.name())
                .files(images)
                .build();
        return List.of(fixture1, fixture2);
    }
}
