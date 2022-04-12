package com.example.itaminbackend.domain.myfile.repository;

import com.example.itaminbackend.domain.myfile.entity.MyFile;

import java.util.Optional;

public interface MyFileRepositoryCustom {
    Optional<MyFile> findNotDeletedByFileId(Long fileId);
}
