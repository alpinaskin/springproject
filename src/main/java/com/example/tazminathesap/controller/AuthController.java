package com.example.tazminathesap.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.tazminathesap.dto.request.LoginRequest;
import com.example.tazminathesap.dto.response.JwtResponse;
import com.example.tazminathesap.model.ERole;
import com.example.tazminathesap.model.Role;
import com.example.tazminathesap.model.User;
import com.example.tazminathesap.model.UserDetailsImpl;
import com.example.tazminathesap.repository.RoleRepository;
import com.example.tazminathesap.repository.UserRepository;
import com.example.tazminathesap.security.jwt.JwtUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin") 
    public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest user){
        Authentication authentication = authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
             .map(item -> item.getAuthority())
             .collect(Collectors.toList());
        
        return ResponseEntity.ok()
            .body(new JwtResponse(jwt, userDetails.getId(), userDetails.getName(), userDetails.getLastName(), userDetails.getEmail(), roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if(userRepository.existsByEmail(user.getEmail()))
            return ResponseEntity.badRequest().body("Böyle bir email var!");
        
        User userToSave = user;
        userToSave.setPassword(encoder.encode(user.getPassword()));
        
        List<Role> roles = new ArrayList<>();
        Role newRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("rol yok?"));
        roles.add(newRole);

        userToSave.setRoles(roles);
        userRepository.save(userToSave);

        return ResponseEntity.ok().body("Kullanıcı kaydedildi");
    }
}
