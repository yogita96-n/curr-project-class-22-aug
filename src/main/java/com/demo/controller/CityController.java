package com.demo.controller;

import com.demo.entity.City;
import com.demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/city")
public class CityController {
    @Autowired
    private CityService cityService;
    @PostMapping("/login")
    public ResponseEntity<City>addCity(@RequestBody City city){
        City city1 = cityService.addCity(city);
        return new ResponseEntity<>(city1, HttpStatus.CREATED);
    }
}
