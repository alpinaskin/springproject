package com.example.tazminathesap.security.jwt;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.example.tazminathesap.model.UserDetailsImpl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

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
    //@Value("${tazminathesap.app.jwtCookie}")
    private String jwtCookie="bilirkisi";

    public String getJwtFromCookie(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, jwtCookie);
        if(cookie != null)
            return cookie.getValue();
        else
            return null;
    }

    public ResponseCookie generateJwtCookie (UserDetailsImpl userPrincipal) {
        String jwt = generateTokenFromEmail(userPrincipal.getEmail());
        ResponseCookie cookie = ResponseCookie.from(jwtCookie, jwt).path("/api/v1").maxAge(24 * 60 * 60).httpOnly(true).build();
        return cookie;
    }

    public ResponseCookie getCleanJwtCookie () {
        ResponseCookie cookie = ResponseCookie.from(jwtCookie, null).path("/api/v1").build();
        return cookie;
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

    public String generateTokenFromEmail(String email) {
        return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();
    }
}
