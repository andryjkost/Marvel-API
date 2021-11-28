package com.andryjkost.superheroes.controllers;

import com.andryjkost.superheroes.dto.ComicDtoIn;
import com.andryjkost.superheroes.entities.Character;
import com.andryjkost.superheroes.entities.Comic;
import com.andryjkost.superheroes.repositories.CharacterRepository;
import com.andryjkost.superheroes.repositories.ComicRepository;

import com.andryjkost.superheroes.services.CharacterService;
import com.andryjkost.superheroes.services.ComicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/public/comics")
public class ComicController {
    private final ComicService comicService;

    @Operation(
            summary = "Search comics",
            description = "Fetches lists of comics with optional filters."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Success"
    )
    @GetMapping
    public Page<Comic> findComics(Pageable pageable,
                                                  @RequestParam(required = false) String title,
                                                  @RequestParam(required = false, defaultValue = "") String titleStartsWith) {

        return comicService.getComicsbyTitle(pageable,title,titleStartsWith);
    }

    @Operation(
            summary = "Search characters by comic's id",
            description = "Fetches lists of characters with optional filters."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Success"
    )
    @GetMapping("/{id}/characters")
    public Page<Character> findCharacterByComicId(
            Pageable pageable,
            @PathVariable(value = "id") Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false, defaultValue = "") String nameStartsWith) {
            return comicService.getCharactersByComicId(pageable,id,name,nameStartsWith);
    }

    @Operation(
            summary = "Search comic by id",
            description = "This method fetches a single comic resource."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success"
            ),
            @ApiResponse(
                    responseCode = "200",
                    description = "Comic not found."
            )
    })
    @GetMapping("/{id}")
    public Comic findComicById(
            @PathVariable(value = "id") Long id) {
        return comicService.getComicById(id);
    }

    @Operation(
            summary = "Create new comic"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Comic created"
    )

    @PostMapping
    public Comic createComic(@RequestBody ComicDtoIn comic) {
        return comicService.createComic(comic);
    }

    @Operation(
            summary = "Update comic",
            description = "Edit comic that already exist"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Comic edited"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Comic not found"
            )
    })
    @PutMapping("/{id}")
    public Comic updateComic(@RequestBody ComicDtoIn comic,
                                                 @PathVariable Long id) {

        return comicService.updateComic(comic,id);
    }
}