package com.avidea.avitrain.models;

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
    @NotNull
    private String description;
    @NotNull
    private String link;
    @ManyToOne
    @JoinColumn(name="claim_id")
    private Claim claim;

}
