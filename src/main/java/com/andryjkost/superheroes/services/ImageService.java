package com.andryjkost.superheroes.services;

import com.andryjkost.superheroes.entities.Image;
import com.andryjkost.superheroes.exceptions.MarvelException;
import com.andryjkost.superheroes.repositories.FileSystemRepository;
import com.andryjkost.superheroes.repositories.ImageRepository;


import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Service
public class ImageService {

    private final FileSystemRepository fileSystemRepository;
    private final ImageRepository imageRepository;

    public Image createImage(byte[] bytes, String imageName) throws Exception {
        String location = fileSystemRepository.save(bytes, imageName);
        return imageRepository.save(new Image(imageName, location));
    }

    public FileSystemResource getImageById(Long id) {
        Image image = imageRepository.findById(id).orElseThrow(() -> new MarvelException(HttpStatus.NOT_FOUND, "Can't find image with id=" + id));
        try {
            FileSystemResource resource = fileSystemRepository
                    .findInFileSystem(image.getPath());
            return resource;
        } catch (Exception e) {
            throw new MarvelException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public FileSystemResource downloadImage(Long id) {
        FileSystemResource image = getImageById(id);
        return image;
    }
    public Image uploadImage(MultipartFile image) throws Exception {
        return createImage(image.getBytes(), image.getOriginalFilename());
    }

}
