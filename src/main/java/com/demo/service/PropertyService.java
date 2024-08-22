package com.demo.service;

import com.demo.entity.City;
import com.demo.entity.Country;
import com.demo.entity.Property;
import com.demo.repo.CityRepository;
import com.demo.repo.CountryRepository;
import com.demo.repo.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CountryRepository countryRepository;
    public Property addProperty(Property property, long cityId, long countryId) {

        Optional<City> byId = cityRepository.findById(cityId);
        City city = byId.get();

        Optional<Country> opCountry = countryRepository.findById(countryId);
        Country country = opCountry.get();
        property.setCity(city);
        property.setCountry(country);
        Property save = propertyRepository.save(property);
        return save;
    }

    public List<Property> getPropertyDetails(String cityName) {
        List<Property> properties = propertyRepository.searchProperty(cityName);
    return properties;
    }
}
