package com.andryjkost.superheroes.repositories;

import com.andryjkost.superheroes.entities.Comic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComicRepository extends JpaRepository<Comic, Long> {

    Page<Comic> findByTitle(Pageable pageable, String title);

    Page<Comic> findByTitleStartsWithOrderByTitle(Pageable pageable, String titleStartsWith);

    Page<Comic> findByCharacters_Id_AndTitle(Pageable pageable, Long charId, String title);

    Page<Comic> findByCharacters_Id_AndTitleStartsWithOrderByTitle(Pageable pageable, Long charId,
                                                                   String startsWith);
}
