package com.avidea.avitrain.repositories;

import com.avidea.avitrain.models.Claim;
import com.avidea.avitrain.models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    List<Photo> findAllByClaim(Claim claim);
}
