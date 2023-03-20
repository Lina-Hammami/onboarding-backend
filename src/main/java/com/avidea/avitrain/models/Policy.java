package com.avidea.avitrain.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@Table(name="policies")
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long policyId;
    @NotNull
    @Column(unique = true)
    private String policyNb;
    @NotNull
    private String vehiculeNb;
    @NotNull
    private String insuredName;
    @NotNull
    private Date startDate;
    @NotNull
    private Date endDate;
    @JsonManagedReference
    @OneToMany(
            mappedBy="policy",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Claim> claims = new ArrayList<>();

    public Policy(){

    }
    public Policy(String policyNb, String vehiculeNb, String insuredName, Date startDate, Date endDate) {
        this.policyNb = policyNb;
        this.vehiculeNb = vehiculeNb;
        this.insuredName = insuredName;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public Long getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Long policyId) {
        this.policyId = policyId;
    }

    public String getPolicyNb() {
        return policyNb;
    }

    public void setPolicyNb(String policyNb) {
        this.policyNb = policyNb;
    }

    public String getVehiculeNb() {
        return vehiculeNb;
    }

    public void setVehiculeNb(String vehiculeNb) {
        this.vehiculeNb = vehiculeNb;
    }

    public String getInsuredName() {
        return insuredName;
    }

    public void setInsuredName(String insuredName) {
        this.insuredName = insuredName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Claim> getClaims() {
        return claims;
    }

    public void setClaims(List<Claim> claims) {
        this.claims = claims;
    }

}
