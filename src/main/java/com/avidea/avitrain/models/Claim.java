package com.avidea.avitrain.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "claims")
public class Claim {

    public enum Status{
        OPEN,
        EXPERTISE,
        CLOSED
    };
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long claimId;
    @NotNull
    private String claimNb;
    @NotNull
    private Status status;
    @NotNull
    private Date accidentDate;
    @NotNull
    private Date creationDate;
    @NotNull
    @ManyToOne
    @JoinColumn(name="policy_id")
    private Policy policy;
    @OneToMany(
            mappedBy="photo",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Claim> photos = new ArrayList<>();


    public Claim(Long claimId, String claimNb, Status status, Date accidentDate, Date creationDate, Policy policy) {
        this.claimId = claimId;
        this.claimNb = claimNb;
        this.status = status;
        this.accidentDate = accidentDate;
        this.creationDate = creationDate;
        this.policy = policy;
    }
    public Long getClaimId() {
        return claimId;
    }
    public void setClaimId(Long claimId) {
        this.claimId = claimId;
    }

    public String getClaimNb() {
        return claimNb;
    }

    public void setClaimNb(String claimNb) {
        this.claimNb = claimNb;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    public Policy getPolicy() {
        return policy;
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
    }

    public Date getAccidentDate() {
        return accidentDate;
    }

    public void setAccidentDate(Date accidentDate) {
        this.accidentDate = accidentDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }


}
