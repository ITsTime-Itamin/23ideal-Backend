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
        List<Image> images = new ArrayList<>();
        if(multipartFiles!=null)
        for (MultipartFile multipartFile : multipartFiles)
            images.add(this.myFileRepository.save(this.myFileMapper.toEntity(this.fileService.saveFile(multipartFile))));
        return images;
    }

    @Override
    public Image saveImage(MultipartFile multipartFile) {
        return this.myFileRepository.save(Image.from(this.fileService.saveFile(multipartFile)));
    }

    @Override
    public Image getImage(Long imageId) {
        return this.myFileRepository.findNotDeletedByImageId(imageId).orElseThrow(NotFoundImageException::new);
    }

    @Override
    @Transactional
    public boolean deleteImage(Long imageId) {
        Image image = this.findByImageId(imageId);
        image.setDeleted(true);
        return true;
    }

    private Image findByImageId(Long imageId) {
        return this.myFileRepository.findById(imageId).orElseThrow(NotFoundImageException::new);
    }
}
