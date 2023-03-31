package com.avidea.avitrain.repositories;

import com.avidea.avitrain.models.Claim;
import com.avidea.avitrain.models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
    @Modifying
    @Query("update Claim c set c.claimNb = :claimNb where c.id = :id")
    void updateClaimNb(@Param(value = "id") long id, @Param(value = "claimNb") String claimNb);
    @Modifying
    @Query("update Claim c set c.status = :status where c.id = :id")
    void updateStatus(@Param(value = "id") long id, @Param(value = "status") String status);
    @Modifying
    @Query("update Claim c set c.accidentDate = :accidentDate where c.id = :id")
    void updateAccidentDate(@Param(value = "id") long id, @Param(value = "accidentDate") Date accidentDate);
    @Modifying
    @Query("update Claim c set c.creationDate = :creationDate where c.id = :id")
    void updateCreationDate(@Param(value = "id") long id, @Param(value = "creationDate") Date creationDate);
}
