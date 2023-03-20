package com.avidea.avitrain.repositories;

import com.avidea.avitrain.models.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long> {
}
