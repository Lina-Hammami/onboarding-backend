package com.avidea.avitrain.controllers;

import com.avidea.avitrain.models.Claim;
import com.avidea.avitrain.models.Policy;
import com.avidea.avitrain.services.ClaimService;
import com.avidea.avitrain.services.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200", maxAge = 3600)
public class PolicyController {
    @Autowired
    private ClaimService claimService;
    @Autowired
    private PolicyService policyService;

    @PostMapping("/policies/add")
    public ResponseEntity<Policy> addPolicy(@RequestBody Policy policy)  {
        try {
            Policy p = policyService.addPolicy(policy);
            return new ResponseEntity<>(p, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }
    @GetMapping("/policies/{id}")
    public ResponseEntity<Policy> getPolicy(@PathVariable long id){
        Policy policy =  policyService.getPolicy(id);
        if (policy != null) {
            return new ResponseEntity<>(policy, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/policies/nb/{nb}")
    public ResponseEntity<Policy> getPolicyByNumber(@PathVariable String nb){
        Policy policy =  policyService.getPolicyByNumber(nb);
        if (policy != null) {
            return new ResponseEntity<>(policy, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("claims/{id}/policy")
    public ResponseEntity<Policy> getPolicyByClaimId(@PathVariable("id") Long claimId){
        try {
            Long pId = claimService.getClaim(claimId).getPolicy().getPolicyId();
            Policy policy = policyService.getPolicy(pId);
            if (policy==null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(policy, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/policies/list")
    public ResponseEntity<List<Policy>> getPoliciesList(){
        try {
            List<Policy> policies = policyService.getPolicies();
            if (policies==null || policies.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(policies, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/policies/nbs")
    public ResponseEntity<List<String>> getPoliciesNumbers(){
        try {
            List<String> numbers = policyService.getAllPNumbers();
            return new ResponseEntity<>(numbers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
