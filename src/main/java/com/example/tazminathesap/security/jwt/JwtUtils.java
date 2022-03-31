package com.example.tazminathesap.security.jwt;

import java.util.Date;

import com.example.tazminathesap.model.UserDetailsImpl;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtUtils {
    //@Value("${tazminathesap.app.jwtSecret}")
    private String jwtSecret = "HRlELXqpSB";
    //@Value("${tazminathesap.app.jwtExpirationMs}")
    private int jwtExpirationMs = 86400000;

    public String generateJwtToken (Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
            .setSubject((userPrincipal.getUsername()))
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();
    }

    public String getEmailFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken).getBody().getSubject();
            return true;
        } catch (SignatureException e) {
            log.error("Yanlış JWT imzası: {}", e.getMessage());
        } catch(MalformedJwtException e) {
            log.error("Yanlış token: {}", e.getMessage());
        } catch( ExpiredJwtException e ) {
            log.error("Süresi geçmiş JWT tokeni: {}", e.getMessage());
        } catch( UnsupportedJwtException e) {
            log.error("Desteklenmeyen JWT tokeni : {}", e.getMessage());
        } catch( IllegalArgumentException e ) {
            log.error("JWT boş string : {}", e.getMessage());
        }
        return false;
    }
}
