package com.avidea.avitrain.repositories;

import com.avidea.avitrain.models.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long> {

    Policy findByPolicyNb(String number);

    @Query(value = "SELECT p.policyNb FROM Policy p ORDER BY p.policyNb")
    List<String> findAllNumbers();
}
