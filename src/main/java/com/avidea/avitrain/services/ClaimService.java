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

    public Claim updateClaim(Claim claim, long id) {
        Claim myclaim = claimRepository.findById(id).orElse(null);
        if(claim.getClaimNb() != null && myclaim.getClaimNb() != claim.getClaimNb()) {
            claimRepository.updateClaimNb(id,claim.getClaimNb());
        }
        if(claim.getStatus() != null && !myclaim.getStatus().toString().equals(claim.getStatus().toString())) {
            claimRepository.updateStatus(id,claim.getStatus());
        }
        if(claim.getAccidentDate() != null && !myclaim.getAccidentDate().equals(claim.getAccidentDate()))  {
            claimRepository.updateAccidentDate(id,claim.getAccidentDate());
        }
        if(claim.getCreationDate() != null && !myclaim.getCreationDate().equals(claim.getCreationDate()))  {
            claimRepository.updateCreationDate(id,claim.getCreationDate());
        }
        return myclaim;
    }

}
