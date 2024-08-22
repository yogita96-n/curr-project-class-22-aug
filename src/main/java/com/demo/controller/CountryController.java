package com.demo.controller;

import com.demo.entity.Country;
import com.demo.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/country")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @PostMapping()
    public ResponseEntity<Country>addCountry(@RequestBody Country country){
        Country country1 = countryService.addCountry(country);
        return new ResponseEntity<>(country1,HttpStatus.CREATED);
    }
}
