package com.appointments.trackpoint.util;

import com.appointments.trackpoint.model.AuthUserDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtility {

    private final String SECRET_KEY = "a3e8f9d4c7b6e5a2c1b0d3e6f5c8a9d2e1b4d7a0c3f2e1d0a3c6e5a8d9b0c1e2";  // Choose a strong secret key

    public void generateTokenInCookie(AuthUserDTO authUserDTO, HttpServletResponse response) {
        // Generate the token.
        String jwtToken = generateToken(authUserDTO);

        // Create the HTTP-only cookie.
        Cookie jwtCookie = new Cookie("TOKEN", jwtToken);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setPath("/");
        jwtCookie.setSecure(false);
        // Optional: Set the cookie secure flag if serving over HTTPS.
        // jwtCookie.setSecure(true);

        response.addCookie(jwtCookie);

    }

    private String generateToken(AuthUserDTO authUserDTO) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", authUserDTO.getUsername());
        claims.put("name", authUserDTO.getName());
        claims.put("category", authUserDTO.getCategory());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
