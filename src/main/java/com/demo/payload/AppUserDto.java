package com.demo.payload;

import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppUserDto {
    private long id;
    private String name;
    private String username;
    private String email;
    private String password;


}
