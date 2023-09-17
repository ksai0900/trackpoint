package com.appointments.trackpoint.util;

import com.appointments.trackpoint.model.AuthUserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtility {
    private final static Logger LOGGER = LoggerFactory
            .getLogger(JwtUtility.class);

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
        claims.put("id", authUserDTO.getId());
        claims.put("username", authUserDTO.getUsername());
        claims.put("name", authUserDTO.getName());
        claims.put("category", authUserDTO.getCategory());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public AuthUserDTO extractAuthUserDTO(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();

            AuthUserDTO authUserDTO = new AuthUserDTO();
            authUserDTO.setId(claims.get("id", Long.class));
            authUserDTO.setUsername(claims.get("username", String.class));
            authUserDTO.setName(claims.get("name", String.class));
            authUserDTO.setCategory(claims.get("category", String.class));

            return authUserDTO;
        } catch (SignatureException e) {
            LOGGER.error("Invalid JWT signature: {}", e.getMessage());
            return null;
        }
    }

}
