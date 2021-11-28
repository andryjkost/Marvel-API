package com.andryjkost.superheroes.controllers;


import com.andryjkost.superheroes.entities.Character;
import com.andryjkost.superheroes.entities.Comic;
import com.andryjkost.superheroes.repositories.CharacterRepository;
import com.andryjkost.superheroes.repositories.ComicRepository;


import com.andryjkost.superheroes.services.CharacterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@RequestMapping("/v1/public/characters")
public class CharacterController {
    private final CharacterService characterService;
    @Operation(
            summary = "Search characters",
            description = "Retrieves lists of comic book characters with additional filters."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Success"
    )
    @GetMapping
    public Page<Character> findCharacters(Pageable pageable,
                                                          @RequestParam(required = false) String name,
                                                          @RequestParam(required = false, defaultValue = "") String nameStartsWith) {

        return characterService.getCharactersByName(pageable,name,nameStartsWith);
    }

    @Operation(
            summary = "Search character by id",
            description = "This method retrieves a single character resource."
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

    @GetMapping("/{id}")
    public Character findCharacterById(
            @PathVariable(value = "id") Long id) {
        return characterService.getCharacterByID(id);
    }

    @Operation(
            summary = "Search comics by character's ID`",
            description = "Retrieves lists of comics with additional filters."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Success"
    )
    @GetMapping("/{id}/comics")
    public Page<Comic> findComicsByCharacterId(
            Pageable pageable,
            @PathVariable(value = "id") Long id,
            @RequestParam(required = false) String title,
            @RequestParam(required = false, defaultValue = "") String titleStartsWith) {
        return characterService.getComicsByCharacterId(pageable,id,title,titleStartsWith);
    }

    @Operation(
            summary = "Create a new character"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Character created"
    )
    @PostMapping
    public Character createCharacter(@RequestBody Character character) {
        return characterService.createCharacter(character);
    }

    @Operation(
            summary = "Update character",
            description = "Edit character that already exist"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Character edited"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Character not found"
            )
    })
    @PutMapping("/{id}")
    public Character updateCharacter(@RequestBody Character character,
                                     @PathVariable Long id) {
       return characterService.updateCharacter(character,id);
    }
}
