package com.andryjkost.superheroes.services;

import com.andryjkost.superheroes.dto.ComicDtoIn;
import com.andryjkost.superheroes.entities.Character;
import com.andryjkost.superheroes.entities.Comic;
import com.andryjkost.superheroes.exceptions.MarvelException;
import com.andryjkost.superheroes.repositories.CharacterRepository;
import com.andryjkost.superheroes.repositories.ComicRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Service
public class ComicService {
    private final CharacterRepository characterRepository;
    private final ComicRepository comicRepository;
    private final CharacterService characterService;
    private final ModelMapper modelMapper;

    public Page<Comic> getComicsbyTitle(Pageable pageable, String title, String titleStartsWith) {
        Page<Comic> page;
        if (title != null) {
            page = comicRepository.findByTitle(pageable, title);
        } else {
            page = comicRepository.findByTitleStartsWithOrderByTitle(pageable, titleStartsWith);
        }
        return page;
    }

    public Comic getComicById(Long id) {
        return comicRepository.findById(id).orElseThrow(() -> new MarvelException(HttpStatus.NOT_FOUND, "Can't find comic with id=" + id));

    }

    public Page<Character> getCharactersByComicId(Pageable pageable, Long id, String name, String nameStartsWith) {
        if (name != null) {
            return characterRepository.findByComics_Id_AndName(pageable, id, name);
        } else {
            return characterRepository.findByComics_Id_AndNameStartingWithOrderByName(pageable, id, nameStartsWith);
        }
    }

    public Comic createComic(ComicDtoIn comicdto) {
        return comicRepository.save(comicMapper(comicdto));
    }

    public Comic updateComic(ComicDtoIn comicdto, Long id) {
        if (!comicRepository.existsById(id)) {
            throw new MarvelException(HttpStatus.NOT_FOUND, "Can't find comic with id=" + id);
        } else {
            Comic comic =comicMapper(comicdto);
            comic.setId(id);
            return comicRepository.save(comic);
        }
    }

    private Comic comicMapper(ComicDtoIn comicdto){
        Set<Character> characters = new HashSet<>();
        for (Long character : comicdto.getCharacters()) {
            characters.add(characterService.getCharacterByID(character));
        }
        Comic comic = modelMapper.map(comicdto,Comic.class);
        comic.setCharacters(characters);
        return comic;
    }
}
