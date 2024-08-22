package com.demo.controller;

import com.demo.entity.AppUser;
import com.demo.payload.AppUserDto;
import com.demo.payload.LoginDto;
import com.demo.payload.TokenDto;
import com.demo.repo.AppUserRepository;
import com.demo.service.AppUserService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/login")
@JsonIgnoreProperties({"pageNo","pageSize","field"})
public class AppUserController {
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private AppUserRepository appUserRepository;

    @PostMapping("/create-user")//for user specific
    public ResponseEntity<AppUserDto> addDetails(@RequestBody AppUserDto appUserDto){
        AppUserDto appUserDto1 = appUserService.addDetails(appUserDto);
        return new ResponseEntity<>(appUserDto1, HttpStatus.CREATED);
    }

    @PostMapping("/create-property-owner")//for user specific
    public ResponseEntity<AppUserDto> propertyOwnerDetails(@RequestBody AppUserDto appUserDto){
        AppUserDto appUserDto1 = appUserService.propertyOwnerDetails(appUserDto);
        return new ResponseEntity<>(appUserDto1, HttpStatus.CREATED);
    }
    @PostMapping("/create-propertyManager")//for user specific
    public ResponseEntity<AppUserDto> propertyManagerDetails(@RequestBody AppUserDto appUserDto){
        AppUserDto appUserDto1 = appUserService. propertyManagerDetails(appUserDto);
        return new ResponseEntity<>(appUserDto1, HttpStatus.CREATED);
    }
    @PostMapping("/verify")
    public ResponseEntity<?>verifyLogin(@RequestBody LoginDto loginDto){
        String token= appUserService.verifyLogin(loginDto);
if (token!=null){
    TokenDto tokenDto=new TokenDto();
    tokenDto.setTokenType("jwt");
    tokenDto.setToken(token);
    return new ResponseEntity<>(tokenDto,HttpStatus.OK);
}else {
    return new ResponseEntity<>("invalid credentials", HttpStatus.UNAUTHORIZED);
}

    }
    //use pagination
    @GetMapping("/get")
    public ResponseEntity<List<AppUser>>getDetailsPagination(
            @RequestParam (name="pageNo",defaultValue = "0",required = false)int pageNo,
            @RequestParam(name = "pageSize",defaultValue = "4",required = false) int pageSize,
            @RequestParam(name = "field",defaultValue = "name",required = false) String field){
        Page<AppUser> all = appUserRepository.findAll(PageRequest.of(pageNo, pageSize).withSort(Sort.by(field)));
        List<AppUser> content = all.getContent();
        return new ResponseEntity<>(content,HttpStatus.OK);
    }
}
