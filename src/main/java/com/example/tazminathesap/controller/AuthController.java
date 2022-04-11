package com.example.tazminathesap.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.tazminathesap.dto.request.LogOutRequest;
import com.example.tazminathesap.dto.request.LoginRequest;
import com.example.tazminathesap.dto.request.PasswordChangeRequest;
import com.example.tazminathesap.dto.request.TokenRefreshRequest;
import com.example.tazminathesap.dto.response.JwtResponse;
import com.example.tazminathesap.dto.response.TokenRefreshResponse;
import com.example.tazminathesap.exception.TokenRefreshException;
import com.example.tazminathesap.model.ERole;
import com.example.tazminathesap.model.RefreshToken;
import com.example.tazminathesap.model.Role;
import com.example.tazminathesap.model.User;
import com.example.tazminathesap.model.UserDetailsImpl;
import com.example.tazminathesap.repository.RoleRepository;
import com.example.tazminathesap.repository.UserRepository;
import com.example.tazminathesap.security.jwt.JwtUtils;
import com.example.tazminathesap.service.RefreshTokenService;

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
    @Autowired
    RefreshTokenService refreshTokenService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest user) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwtToken(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

        return ResponseEntity.ok()
                .body(new JwtResponse(jwt, refreshToken.getToken(), userDetails.getId(), userDetails.getName(),
                        userDetails.getLastName(), userDetails.getEmail(), roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userRepository.existsByEmail(user.getEmail()))
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

    @PostMapping("/changepassword")
    public ResponseEntity<?> changePassword(@RequestBody PasswordChangeRequest user) {
        if (!userRepository.existsByEmail(user.getEmail()))
            return ResponseEntity.badRequest().body("Kullanıcı bulunamadı!");

        User userToBeChanged = userRepository.findByEmail(user.getEmail());

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getOldPassword()));

        if (authentication.isAuthenticated())
            userToBeChanged.setPassword(encoder.encode(user.getNewPassword()));
        else
            return ResponseEntity.badRequest().body("Kullanıcı Şifresini Yanlış Girdiniz");

        userRepository.save(userToBeChanged);

        return ResponseEntity.ok().body("Kullanıcı Şifresi Başarıyla Değiştirildi!");
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshToken(@RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromUsername(user.getEmail());
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken, "Refresh Token bulunamadı!"));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(@RequestBody LogOutRequest logOutRequest) {
        refreshTokenService.deleteByUserId(logOutRequest.getUserId());
        return ResponseEntity.ok("Başarılı şekilde çıkıldı");
    }
}
