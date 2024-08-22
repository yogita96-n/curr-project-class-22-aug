package com.demo.service;

import com.demo.entity.City;
import com.demo.repo.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {
@Autowired
private CityRepository cityRepository;
    public City addCity(City city) {
        return cityRepository.save(city);
    }
}
