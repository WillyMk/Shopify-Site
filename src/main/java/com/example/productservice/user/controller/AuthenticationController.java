package com.example.productservice.user.controller;

import com.example.productservice.user.dto.LoginDto;
import com.example.productservice.user.dto.LoginResponse;
import com.example.productservice.user.dto.UserDto;
import com.example.productservice.user.entity.User;
import com.example.productservice.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto){
        User user = userService.registerUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("success");
    }

    @PostMapping("login")
    public ResponseEntity<?> userLogin(@RequestBody LoginDto loginDto){
        String jwt = userService.loginUser(loginDto);
        if(jwt == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
        }else{
            LoginResponse response = new LoginResponse();
            response.setJwt(jwt);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<User> getLoggedInUser(@AuthenticationPrincipal User user){
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
