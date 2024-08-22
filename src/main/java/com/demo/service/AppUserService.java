package com.demo.service;

import com.demo.entity.AppUser;
import com.demo.payload.AppUserDto;
import com.demo.payload.LoginDto;
import com.demo.repo.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService {
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private JWTService jwtService;
    public AppUserDto addDetails(AppUserDto appUserDto) {
        AppUser appUser = mapToEntity(appUserDto);
       PasswordEncoder pass =new BCryptPasswordEncoder();
       appUser.setPassword(pass.encode(appUser.getPassword()));
       appUser.setRole("ROLE_USER");
        AppUser user = appUserRepository.save(appUser);
        return mapToDto(user);

    }
    AppUser mapToEntity (AppUserDto appUserDto){
        AppUser appUser=new AppUser();
        appUser.setId(appUserDto.getId());
        appUser.setName(appUserDto.getName());
        appUser.setEmail(appUserDto.getEmail());
        appUser.setPassword(appUserDto.getPassword());
        appUser.setUsername(appUserDto.getUsername());
        return appUser;
    }
    AppUserDto mapToDto (AppUser appUser){
        AppUserDto appUserDto=new AppUserDto();
        appUserDto.setId(appUser.getId());
        appUserDto.setName(appUser.getName());
        appUserDto.setEmail(appUser.getEmail());
        appUserDto.setPassword(appUser.getPassword());
        appUserDto.setUsername(appUser.getUsername());
        return appUserDto;
    }


    public String verifyLogin(LoginDto loginDto) {
        Optional<AppUser> opUser = appUserRepository.findByUsername(loginDto.getUsername());
        AppUser appUser = opUser.get();
        if(BCrypt.checkpw(loginDto.getPassword(),appUser.getPassword())){
            return jwtService.generateToken(appUser);
        }
        return null;
    }

    public AppUserDto propertyOwnerDetails(AppUserDto appUserDto) {
        AppUser appUser = mapToEntity(appUserDto);
        appUser.setRole("ROLE_PROPERTY_OWNER");
       PasswordEncoder pass= new BCryptPasswordEncoder();
       appUser.setPassword(pass.encode(appUser.getPassword()));
        AppUser saveEntity = appUserRepository.save(appUser);
        return mapToDto(saveEntity);
    }

    public AppUserDto propertyManagerDetails(AppUserDto appUserDto) {
        AppUser appUser = mapToEntity(appUserDto);
       PasswordEncoder pass=new BCryptPasswordEncoder();
       appUser.setPassword(pass.encode(appUser.getPassword()));
       appUser.setRole("ROLE_ADMIN");
        AppUser saveEntity = appUserRepository.save(appUser);
       return mapToDto(saveEntity);
    }
}

