package com.example.itaminbackend.domain.myfile.service;

import com.example.itaminbackend.domain.myfile.entity.MyFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MyFileService{

    List<MyFile> saveImages(List<MultipartFile> multipartFiles);
}
