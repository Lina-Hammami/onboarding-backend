package com.avidea.avitrain.models;

import javax.persistence.*;
import java.util.Date;

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
    private String claimNb;
    private Status status;
    private Date accidentDate;
    private Date creationDate;

    public Long getClaimId() {
        return claimId;
    }

    public Claim() {
    }

    public Claim(Long claimId, String claimNb, Status status, Date accidentDate, Date creationDate) {
        this.claimId = claimId;
        this.claimNb = claimNb;
        this.status = status;
        this.accidentDate = accidentDate;
        this.creationDate = creationDate;
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
