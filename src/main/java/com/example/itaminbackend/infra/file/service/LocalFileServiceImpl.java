package com.example.itaminbackend.infra.file.service;

import com.amazonaws.util.IOUtils;
import com.example.itaminbackend.infra.file.constant.FileConstants.ELocalFileServiceImpl;
import com.example.itaminbackend.infra.file.exception.FileSaveFailedException;
import com.example.itaminbackend.infra.file.util.ImageExtension;
import com.example.itaminbackend.infra.file.exception.ImageNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Profile("dev")
@Service
public class LocalFileServiceImpl implements FileService {

    @Override
    public String saveFile(MultipartFile file) {
        try {
            String extension = Objects.requireNonNull(FilenameUtils.getExtension(file.getOriginalFilename()));
            ImageExtension.validateImageExtension(extension);
            File savedFile = new File(String.format(ELocalFileServiceImpl.eFileStringFormat.getValue(), ELocalFileServiceImpl.eImagePath.getValue(), UUID.randomUUID(), extension));
            savedFile.getParentFile().mkdirs();
            file.transferTo(savedFile.toPath());
            return savedFile.getName();
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new FileSaveFailedException();
        }
    }

    @Override
    public byte[] getFile(String key) {
        try (final BufferedInputStream bis = new BufferedInputStream(new FileInputStream(ELocalFileServiceImpl.eImagePath.getValue() + key))) {
            return IOUtils.toByteArray(bis);}
        catch (FileNotFoundException e) {throw new ImageNotFoundException();}
        catch (IOException e) {throw new IllegalArgumentException(e.getMessage());}
    }

    @Override
    public void deleteFile(String key) {
        FileUtils.deleteQuietly(new File(ELocalFileServiceImpl.eImagePath.getValue() + key));
    }

    @Override
    public void deleteAll(List<String> keys) {
        keys.forEach(key -> FileUtils.deleteQuietly(new File(ELocalFileServiceImpl.eImagePath.getValue() + key)));
    }
}
