package com.andryjkost.superheroes.repositories;

import com.andryjkost.superheroes.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {}