package com.example.productservice.user.service;

import com.example.productservice.security.JwtService;
import com.example.productservice.exception.APIException;
import com.example.productservice.user.dto.LoginDto;
import com.example.productservice.user.dto.UserDto;
import com.example.productservice.user.entity.User;
import com.example.productservice.user.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepo userRepo;
    private final EncryptService encryptService;
    private final JwtService jwtService;
    public User registerUser(UserDto userDto) {
        if(userRepo.findByUsername(userDto.getUsername()).isPresent() || userRepo.findByEmail(userDto.getEmail()).isPresent()) {
            System.out.println("userDto = " + userDto.getUsername() + " " + userDto.getEmail());
            System.out.println("Data = " + userRepo.findByUsername(userDto.getUsername()) + " " + userRepo.findByEmail(userDto.getEmail()));
            throw APIException.alreadyExists("User already registered!!");
        }

        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        //ENCRYPT PASSWORD
        user.setPassword(encryptService.encryptPassword(userDto.getPassword()));
        return userRepo.save(user);
    }

    public String loginUser(LoginDto loginDto){
        User user = userRepo.findByUsername(loginDto.getUsername()).orElseThrow((() -> APIException.notFound("User not found")));
        //verify user password
        if(encryptService.verifyPassword(loginDto.getPassword(), user.getPassword())){
            return jwtService.generateJwt(user);
        }else {
            return null;
        }
    }

    public User getUserByEmail(String email){
        return userRepo.findByEmail(email).orElseThrow(() -> APIException.notFound("User by email  {0} not found", email));
    }

    public User getUserByUserName(String userName){
        return userRepo.findByUsername(userName).orElseThrow(() -> APIException.notFound("User by userName {0} not found", userName));
    }

    public User getLoggedInUser(@AuthenticationPrincipal User user){
        return user;
    }
}
