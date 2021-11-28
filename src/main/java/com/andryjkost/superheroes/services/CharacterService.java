package com.andryjkost.superheroes.services;


import com.andryjkost.superheroes.entities.Character;
import com.andryjkost.superheroes.entities.Comic;
import com.andryjkost.superheroes.exceptions.MarvelException;
import com.andryjkost.superheroes.repositories.CharacterRepository;
import com.andryjkost.superheroes.repositories.ComicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final ComicRepository comicRepository;

    public Page<Character> getCharactersByName(Pageable pageable, String name, String nameStartsWith) {

        if (name != null) {
            return characterRepository.findByName(pageable, name);
        } else {
            return characterRepository.findByNameStartsWithOrderByName(pageable, nameStartsWith);
        }
    }

    public Character getCharacterByID(Long id) {
        return characterRepository.findById(id).orElseThrow(() -> new MarvelException(HttpStatus.NOT_FOUND, "Can't find character with id=" + id));
    }

    public Page<Comic> getComicsByCharacterId(Pageable pageable, Long id, String title, String titleStartsWith){

        if (title != null) {
            return comicRepository.findByCharacters_Id_AndTitle(pageable, id, title);
        } else {
            return comicRepository
                    .findByCharacters_Id_AndTitleStartsWithOrderByTitle(pageable, id, titleStartsWith);
        }
    }

    public Character createCharacter(Character character){
        return characterRepository.save(character);
    }
    public Character updateCharacter( Character character,Long id){
        if (!characterRepository.existsById(id)) {
            throw new MarvelException(HttpStatus.NOT_FOUND,"Can't find character with id=" + id);
        } else {
            character.setId(id);
            return characterRepository.save(character);
        }
    }
}