package com.demo.authservice.service;

import com.demo.authservice.dto.LoginRequest;
import com.demo.authservice.dto.RegisterRequest;
import com.demo.authservice.entity.Role;
import com.demo.authservice.entity.User;
import com.demo.authservice.repositoy.RoleRepo;
import com.demo.authservice.repositoy.UserRepo;
import com.demo.authservice.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private RoleRepo roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    // Registration
    public User register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Set<Role> roles = request.getRoles().stream()
                .map(roleName -> roleRepository.findById(roleName)
                        .orElseThrow(() -> new RuntimeException("Role not found: " + roleName)))
                .collect(Collectors.toSet());

        user.setRoles(roles);
        userRepository.save(user);
        return user;
    }

    // Login
    public String authenticate(LoginRequest request) {
        User byUsername = userRepository.findByUsername(request.getUsername());
        Set<Role> roles = byUsername.getRoles();
        Optional<Role> first = roles.stream().findFirst();
        String role = null;
        if (first.isPresent()) {
            role = first.get().getName();
        }else {
            throw new RuntimeException("Invalid User OR Role not found");
        }
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(), request.getPassword()
                    )
            );
        } catch (AuthenticationException ex) {
            throw new RuntimeException("Invalid credentials");
        }

        return "Bearer "+ jwtUtil.generateToken(request.getUsername(), role);
    }

    // Validate JWT
    public String validateToken(String token) {
        String username = jwtUtil.extractUsername(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (!jwtUtil.validateToken(token)) {
            throw new RuntimeException("Invalid or expired token");
        }

        return "Token is valid for user: " + username;
    }
}
