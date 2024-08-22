package com.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(name = "description", length = 2000)
    private String description;

    //for MAPPING
    @ManyToOne
    @JoinColumn(name="property_id")
    private Property property;

    @ManyToOne
    @JoinColumn(name="appUser_id")
    private AppUser appUser;

}