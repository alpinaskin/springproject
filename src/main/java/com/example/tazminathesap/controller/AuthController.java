package com.example.tazminathesap.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.tazminathesap.model.ERole;
import com.example.tazminathesap.model.Role;
import com.example.tazminathesap.model.User;
import com.example.tazminathesap.model.UserDetailsImpl;
import com.example.tazminathesap.repository.RoleRepository;
import com.example.tazminathesap.repository.UserRepository;
import com.example.tazminathesap.security.jwt.JwtUtils;
import com.google.common.net.HttpHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    public ResponseEntity<?> authenticateUser(@RequestBody User user){
        Authentication authentication = authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
        // List<String> roles = userDetails.getAuthorities().stream()
        //     .map(item -> item.getAuthority())
        //     .collect(Collectors.toList());
        
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
            .body(userDetails);
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
    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
            .body("Çıkış yaptınız!");
    }
}
