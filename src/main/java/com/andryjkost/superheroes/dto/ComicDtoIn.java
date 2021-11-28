package com.andryjkost.superheroes.dto;

import com.andryjkost.superheroes.entities.Character;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComicDtoIn {
    @Schema(description = " The canonical title of the comic.")
    private String title;


    @Schema(description = " The preferred description of the comic.")
    private String description;


    @Schema(description = "The representative image for this comic.")
    private String thumbnail;

    @Schema(description = "The canonical URL identifier for this resource.")
    private String resourceURI;

    @Schema(description = "A resource list containing the characters which appear in this comic")
    private Set<Long> characters;

}
