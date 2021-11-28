package com.andryjkost.superheroes.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Schema(description = "Character entity")
@Entity
@Table(name = "characters")
public class Character {
    @Schema(description = " The unique ID of the character resource.Automatically generated.")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "The name of the character.")
    @Column(name = "name")
    private String name;

    @Schema(description = " A short bio or description of the character.")
    @Column(name = "description")
    private String description;

    @Schema(description = " The canonical URL identifier for this resource.")
    @Column(name = "resourceURI")
    private String resourceURI;

    @Schema(description = "The representative image for this character.")
    @Column(name = "thumbnail")
    private String thumbnail;

    @Schema(description = " A list of resources containing comics that feature this character.")
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ManyToMany(mappedBy = "characters")
    private Set<Comic> comics;

}
