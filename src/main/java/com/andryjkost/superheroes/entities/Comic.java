package com.andryjkost.superheroes.entities;


import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Set;
import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Schema(description = "Character entity")
@Entity
@Table(name = "comics")
public class Comic {
    @Schema(description = " The unique ID of the comic resource.Automatically generated.")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = " The canonical title of the comic.")
    @Column(name = "title")
    private String title;


    @Schema(description = " The preferred description of the comic.")
    @Column(name = "description")
    private String description;


    @Schema(description = "The representative image for this comic.")
    @Column(name = "thumbnail")
    private String thumbnail;

    @Schema(description = "The canonical URL identifier for this resource.")
    @Column(name = "resourceURI")
    private String resourceURI;

    @Schema(description = "A resource list containing the characters which appear in this comic")
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Character> characters;


}
