package com.example.itaminbackend.infra.file.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    String saveFile(MultipartFile file);
    byte[] getFile(String key);
    void deleteFile(String key);
    void deleteAll(List<String> keys);
}
