package com.avidea.avitrain.controllers;

import com.avidea.avitrain.models.Claim;
import com.avidea.avitrain.models.Photo;
import com.avidea.avitrain.models.Policy;
import com.avidea.avitrain.services.ClaimService;
import com.avidea.avitrain.services.PhotoService;
import com.avidea.avitrain.services.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/claims")
public class ClaimController {
    @Autowired
    private ClaimService claimService;
    @Autowired
    private PolicyService policyService;
    @Autowired
    private PhotoService photoService;

    @GetMapping("/list")
    public ResponseEntity<List<Claim>> getClaimsList(){
        try {
            List<Claim> claims = claimService.getClaims();
            if (claims==null || claims.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(claims, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Claim> getClaim(@PathVariable long id){
        Claim claim =  claimService.getClaim(id);
        if (claim != null) {
            return new ResponseEntity<>(claim, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping(value="/add", consumes = {"application/xml","application/json"})
    public ResponseEntity<Claim>  addClaim(@RequestBody Claim claim)  {
        try {
            Claim c = claimService.addClaim(claim);
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }
    @Transactional
    @RequestMapping(method = RequestMethod.PUT, value="/update/{id}")
    public ResponseEntity<Claim>  updateClaim(@RequestBody Claim claim, @PathVariable long id)  {
        try {
            Claim c = claimService.updateClaim(claim, id);
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus>  deleteClaim(@PathVariable long id) {
        try {
            claimService.deleteClaim(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
