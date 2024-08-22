package com.demo.controller;

import com.demo.entity.City;
import com.demo.entity.Country;
import com.demo.entity.Property;
import com.demo.repo.CityRepository;
import com.demo.repo.CountryRepository;
import com.demo.service.PropertyService;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {
    @Autowired
   private PropertyService propertyService;

    @PostMapping
    public ResponseEntity<Property>addProperty(@RequestBody Property property,
                                               @RequestParam long cityId,
                                               @RequestParam long countryId){

        Property property1 = propertyService.addProperty(property,cityId,countryId);


        return  new ResponseEntity<>(property1, HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Property>>searchProperty(@RequestParam("cityName")
                                                        String cityName ){
        List<Property> propertyDetails = propertyService.getPropertyDetails(cityName);
    return new ResponseEntity<>(propertyDetails,HttpStatus.OK);
    }



}
