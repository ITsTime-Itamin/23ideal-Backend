package com.example.itaminbackend.common.factory;

import com.example.itaminbackend.domain.board.dto.BoardDto;
import com.example.itaminbackend.domain.board.dto.BoardDto.CreateResponse;
import com.example.itaminbackend.domain.board.dto.BoardDto.GetDetailResponse;
import com.example.itaminbackend.domain.board.dto.BoardDto.UpdateResponse;
import com.example.itaminbackend.domain.board.entity.Board;
import com.example.itaminbackend.domain.image.entity.Image;
import com.example.itaminbackend.domain.user.entity.User;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.itaminbackend.domain.board.constant.BoardConstants.EBoardType;
import static com.example.itaminbackend.domain.board.dto.BoardDto.CreateRequest;
import static com.example.itaminbackend.domain.board.dto.BoardDto.UpdateRequest;
import static java.util.stream.Collectors.toList;

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

    public static List<CreateResponse> mockCreateResponses() {
        CreateResponse fixture1 = CreateResponse.builder()
                .boardId(1L)
                .build();
        return List.of(fixture1);
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

    public static List<UpdateResponse> mockUpdateResponses() {
        UpdateResponse fixture1 = UpdateResponse.builder()
                .boardId(1L)
                .build();
        return List.of(fixture1);
    }

    public static List<GetDetailResponse> mockDetailResponses() {
        List<MultipartFile> multipartFileList = List.of(FileFactory.getTestImage1(), FileFactory.getTestImage2());
        multipartFileList.get(0).getOriginalFilename();
        GetDetailResponse fixture1 = GetDetailResponse.builder()
                .boardId(1L)
                .title("title1")
                .content("content1")
                .boardType(EBoardType.FREE.toString())
                .createdDate(LocalDateTime.of(2022, 7, 10, 16, 10, 5))
                .deadLineDate(LocalDateTime.of(2022, 7, 10, 16, 10, 5))
                .imageKeys(List.of(extractImageFrom(multipartFileList).get(0).getImageKey(),
                                extractImageFrom(multipartFileList).get(1).getImageKey()))
                .userName("kimjungwoo")
                .build();
        return List.of(fixture1);
    }

    public static List<Board> mockBoards(){
        List<MultipartFile> multipartFileList = List.of(FileFactory.getTestImage1(), FileFactory.getTestImage2());

        Board fixture1 = Board.builder()
                .boardId(101L)
                .title("title1")
                .content("content1")
                .boardType(EBoardType.FREE)
                .images(extractImageFrom(multipartFileList))
                .build();

        Board fixture2 = Board.builder()
                .boardId(102L)
                .title("title2")
                .content("content2")
                .boardType(EBoardType.NOTICE)
                .images(extractImageFrom(multipartFileList))
                .build();

        return List.of(fixture1, fixture2);
    }

    private static List<String> extractImageNameFrom(List<MultipartFile> multipartFileList) {
        return multipartFileList.stream()
                .map(MultipartFile::getName)
                .collect(toList());
    }

    private static List<Image> extractImageFrom(List<MultipartFile> multipartFileList) {
        List<Image> myFiles = extractImageNameFrom(multipartFileList).stream()
                .map(Image::new)
                .collect(toList());
        return myFiles;
    }

}
