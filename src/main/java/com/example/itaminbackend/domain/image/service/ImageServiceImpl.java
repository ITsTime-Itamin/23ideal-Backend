package com.example.itaminbackend.domain.image.service;

import com.example.itaminbackend.domain.image.dto.ImageMapper;
import com.example.itaminbackend.domain.image.entity.Image;
import com.example.itaminbackend.domain.image.exception.NotFoundImageException;
import com.example.itaminbackend.domain.image.repository.ImageRepository;
import com.example.itaminbackend.infra.file.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final FileService fileService;
    private final ImageRepository myFileRepository;
    private final ImageMapper myFileMapper;

    @Override
    public List<Image> saveImages(List<MultipartFile> multipartFiles) {
        List<Image> myFiles = new ArrayList<>();
        if(multipartFiles!=null)
        for (MultipartFile multipartFile : multipartFiles)
            myFiles.add(this.myFileRepository.save(this.myFileMapper.toEntity(this.fileService.saveFile(multipartFile))));
        return myFiles;
    }

    @Override
    public Image saveImage(MultipartFile multipartFile) {
        return this.myFileRepository.save(Image.from(this.fileService.saveFile(multipartFile)));
    }

    @Override
    public Image getImage(Long fileId) {
        return this.myFileRepository.findNotDeletedByImageId(fileId).orElseThrow(NotFoundImageException::new);
    }

    @Override
    @Transactional
    public boolean deleteImage(Long fileId) {
        Image image = this.findByImageId(fileId);
        image.setDeleted(true);
        this.fileService.deleteFile(image.getImageKey());
        return true;
    }

    private Image findByImageId(Long fileId) {
        return this.myFileRepository.findById(fileId).orElseThrow(NotFoundImageException::new);
    }
}
