package com.example.itaminbackend.infra.file.controller;

import com.example.itaminbackend.infra.file.service.FileService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/s3")
public class FileApiController {

    private final FileService fileService;

    @ApiOperation(value = "Amazon S3에 이미지 업로드 TEST", notes = "Amazon S3에 이미지 업로드 ")
    @PostMapping("/image")
    public ResponseEntity<String> uploadImage(@ApiParam(value="img 파일들(여러 파일 업로드 가능)", required = true) @RequestPart MultipartFile multipartFile) {
        return ResponseEntity.ok(fileService.saveImage(multipartFile));
    }

    @ApiOperation(value = "Amazon S3에 업로드 된 파일을 삭제 TEST", notes = "Amazon S3에 업로드된 이미지 삭제")
    @DeleteMapping("/image")
    public ResponseEntity<Void> deleteImage(@ApiParam(value="img 파일 하나 삭제", required = true) @RequestParam String fileName) {
        fileService.delete(fileName);
        return ResponseEntity.noContent().build();
    }
}
