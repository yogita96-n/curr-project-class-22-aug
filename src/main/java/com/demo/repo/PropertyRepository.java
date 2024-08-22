package com.demo.repo;

import com.demo.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    
    @Query("select p from Property p JOIN p.city c JOIN p.country co where c.name=:name or co.name=:name ")
    List<Property>searchProperty(@Param ("name") String name );



}