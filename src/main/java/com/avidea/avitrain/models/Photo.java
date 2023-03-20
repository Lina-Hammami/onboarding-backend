package com.avidea.avitrain.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name="photos")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long photoId;
    @NotNull
    private String title;
    private String description;
    @NotNull
    @Column(unique = true)
    private String link;
    @NotNull
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="claim_id")
    private Claim claim;

    public Photo(){

    }
    public Photo(String title, String description, String link, Claim claim) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.claim = claim;
    }

    public Photo(Long photoId, String title, String description, String link, Claim claim) {
        this.photoId = photoId;
        this.title = title;
        this.description = description;
        this.link = link;
        this.claim = claim;
    }

    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Claim getClaim() {
        return claim;
    }

    public void setClaim(Claim claim) {
        this.claim = claim;
    }
}
