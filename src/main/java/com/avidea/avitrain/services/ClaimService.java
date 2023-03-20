package com.avidea.avitrain.services;

import com.avidea.avitrain.models.Claim;
import com.avidea.avitrain.repositories.ClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimService {

    @Autowired
    private final ClaimRepository claimRepository;


    public ClaimService(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }

    public List<Claim> getClaims(){
        return claimRepository.findAll();
    }
    public Claim getClaim(long id) {
        return claimRepository.findById(id).orElse(null);
    }
    public Claim addClaim(Claim claim){
        return claimRepository.save(claim);
    }
    public void deleteClaim(long id){
        claimRepository.deleteById(id);
    }

    public Claim updateClaim(Claim claim) {
        // TODO: fix it
        return claimRepository.save(claim);
    }

}
