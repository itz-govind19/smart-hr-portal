package com.demo.authservice.controller;

import com.demo.authservice.constant.Constants;
import com.demo.authservice.dto.AuthResponse;
import com.demo.authservice.dto.LoginRequest;
import com.demo.authservice.dto.RegisterRequest;
import com.demo.authservice.security.JwtUtil;
import com.demo.authservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.demo.authservice.constant.Constants.BASE_URL;

@RestController
@RequestMapping(BASE_URL)
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        String token = userService.authenticate(request);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @GetMapping("/validate-token")
    public ResponseEntity<?> validate(@RequestParam String token) {
        return ResponseEntity.ok(userService.validateToken(token));
    }
}
