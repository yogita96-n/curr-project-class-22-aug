package com.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@Entity
@Getter
@Setter
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "username",nullable = false, unique = true)
    private String username;
@JsonIgnore
    @Column(name = "password",nullable = false, unique = true)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;
@Getter
@Setter
    //for role creation in database
    @Column(name = "role", nullable = false)
    private String role;

}
