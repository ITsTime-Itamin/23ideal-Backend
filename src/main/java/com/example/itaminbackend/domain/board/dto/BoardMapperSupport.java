package com.example.itaminbackend.domain.board.dto;

import com.example.itaminbackend.domain.image.entity.Image;
import com.example.itaminbackend.domain.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class BoardMapperSupport {
    private final ImageService imageService;

    @Named("saveImages")
    public List<Image> saveImages(List<MultipartFile> files) throws IOException {
        return this.imageService.saveImages(files);
    }

    @Named("getImageKeys")
    public List<String> getImageKeys(List<Image> images){
        List<String> imageKeys = new ArrayList<>();
        for (Image image : images) {
            imageKeys.add(image.getImageKey());
        }
        return imageKeys;
    }

    @Named("toLocalDateTime")
    public LocalDateTime toLocalDateTime(String deadLineDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime changedDeadLineDate = LocalDateTime.parse(deadLineDate+" 00:00:00", formatter);
        return changedDeadLineDate;
    }
}
