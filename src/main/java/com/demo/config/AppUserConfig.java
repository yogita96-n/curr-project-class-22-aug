package com.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class AppUserConfig {
    @Autowired
   private JWTFilter jwtFilter;
    @Bean
    public SecurityFilterChain securityFilterChain
            (HttpSecurity http)throws Exception{
       http.addFilterBefore(jwtFilter, AuthorizationFilter.class);
http.authorizeHttpRequests(auth->auth.
        requestMatchers("api/v2/login/create-user",
                "api/v2/login/create-property-owner","api/v2/login/verify","api/v1/property","api/v1/property/search",
                "/api/v2/city/login","api/v1/country","api/v2/login/get").permitAll().
        requestMatchers("api/v2/login/addProperty").hasAnyRole("PROPERTY_OWNER").
        requestMatchers("api/v2/login/create- propertyManager").hasRole("ADMIN").
        anyRequest().authenticated());
http.csrf(cs->cs.disable());
return http.build();

    }
}
