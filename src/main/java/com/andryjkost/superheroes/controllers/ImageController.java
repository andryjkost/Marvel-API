package com.andryjkost.superheroes.controllers;

import com.andryjkost.superheroes.entities.Image;
import com.andryjkost.superheroes.services.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/public/images")
public class ImageController {

    private final ImageService imageService;

    @Operation(
            summary = "Download image",
            description = "Download image from server"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Character not found."
            )
    })
    @GetMapping(value = "/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public FileSystemResource downloadImage(@PathVariable(name = "id") Long id) {
        return imageService.downloadImage(id);
    }


    @Operation(
            summary = "Upload image",
            description = "Upload image to server."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Image successfully uploaded"
    )
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Image uploadImage(@RequestParam MultipartFile image) throws Exception {
        return imageService.uploadImage(image);
    }
}
