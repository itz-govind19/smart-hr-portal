package com.demo.authservice.Controller;

import com.demo.authservice.constant.Constants;
import com.demo.authservice.dto.ApiResponse;
import com.demo.authservice.dto.AuthResponse;
import com.demo.authservice.dto.LoginRequest;
import com.demo.authservice.dto.RegisterRequest;
import com.demo.authservice.entity.User;
import com.demo.authservice.security.JwtUtil;
import com.demo.authservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static com.demo.authservice.constant.Constants.BASE_URL;

@RestController
@RequestMapping(BASE_URL)
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            User user = userService.register(request);
            return ResponseEntity.ok(user);
        } catch (RuntimeException ex) {
            String message = ex.getMessage();
            HttpStatus status = HttpStatus.BAD_REQUEST;

            if ("Username already exists".equals(message)) {
                status = HttpStatus.CONFLICT;
            } else if (message.startsWith("Role not found")) {
                status = HttpStatus.NOT_FOUND;
            }

            return new ResponseEntity<>(
                    new ApiResponse(status.value(), message, LocalDateTime.now()),
                    status
            );
        }
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
