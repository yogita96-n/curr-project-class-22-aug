package com.demo.service;

import com.demo.entity.Country;
import com.demo.repo.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;

    public Country addCountry(Country country) {
        Country save = countryRepository.save(country);
        return save;
    }
}
