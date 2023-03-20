package com.avidea.avitrain.repositories;

import com.avidea.avitrain.models.Claim;
import com.avidea.avitrain.models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
}
