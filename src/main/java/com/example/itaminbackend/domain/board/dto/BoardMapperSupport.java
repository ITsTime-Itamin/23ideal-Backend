package com.example.itaminbackend.domain.board.dto;

import com.example.itaminbackend.domain.myfile.entity.MyFile;
import com.example.itaminbackend.domain.myfile.service.MyFileService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Component
public class BoardMapperSupport {
    private final MyFileService myFileService;

    @Named("saveImages")
    public List<MyFile> saveImages(List<MultipartFile> files) throws IOException {
        return this.myFileService.saveImages(files);
    }
}
