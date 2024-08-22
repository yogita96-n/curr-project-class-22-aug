package com.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "property")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "num_of_beds", nullable = false)
    private Integer numOfBeds;

    @Column(name = "num_of_bathrooms", nullable = false)
    private Integer numOfBathrooms;

    @Column(name = "num_of_bedrooms", nullable = false)
    private Integer numOfBedrooms;

    //MAPPING property to city(n:1), property to country (n:1
    @ManyToOne
    @JoinColumn(name="city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

}