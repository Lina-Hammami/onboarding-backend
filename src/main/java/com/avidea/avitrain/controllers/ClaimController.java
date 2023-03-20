package com.avidea.avitrain.controllers;

import com.avidea.avitrain.models.Claim;
import com.avidea.avitrain.services.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.List;

@RequestMapping("/claims")
@RestController
public class ClaimController {
    @Autowired
    private ClaimService claimService;

    @GetMapping("/list")
    public List<Claim> getClaimsList(){
        return claimService.getClaims();
    }
    @GetMapping("/list/{id}")
    public Claim getClaim(@PathVariable long id){
        return claimService.getClaim(id);
    }
    @PostMapping("/add")
    public void addClaim(@RequestBody Claim claim)  {
        claimService.addClaim(claim);
    }
    @PostMapping("/update/{id}")
    public void updateClaim(@RequestBody Claim claim, @PathVariable long id)  {
        claimService.updateClaim(claim, id);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteClaim(@PathVariable long id) {
        claimService.deleteClaim(id);
    }
}
