package com.avidea.avitrain.services;

import com.avidea.avitrain.models.Claim;
import com.avidea.avitrain.models.Policy;
import com.avidea.avitrain.repositories.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyService {
    @Autowired
    private final PolicyRepository policyRepository;
    public PolicyService(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }
    public List<Policy> getPolicies(){
        return policyRepository.findAll();
    }
    public Policy getPolicy(long id) {
        return policyRepository.findById(id).orElse(null);
    }
    public Policy addPolicy(Policy policy){
        return policyRepository.save(policy);
    }
    public void deletePolicy(long id){
        policyRepository.deleteById(id);
    }

    public Policy getPolicyByNumber(String number) {
        return policyRepository.findByPolicyNb(number);
    }
}
