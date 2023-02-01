package com.brotech.auth.jwt;


import com.brotech.auth.entity.UserDetailsImp;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtUtils {

    @Value("${app.jwtSecret}")
    private String secertkey;
    @Value("${app.jwtExpirationMs}")
    private long expireTime;

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secertkey).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String generateJwtToken(Authentication authentication) {
        UserDetailsImp userDetailsImp = (UserDetailsImp) authentication.getPrincipal();
        return Jwts.builder().setSubject(userDetailsImp.getUsername())
                .setExpiration(new Date(new Date().getTime() + expireTime))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, secertkey)
                .claim("authority", userDetailsImp.getAuthorities()).compact();
    }

    public Authentication getUser(String token) {

        String email = Jwts.parser().setSigningKey(secertkey).parseClaimsJws(token).getBody().getSubject();
        Claims claims = Jwts.parser().setSigningKey(secertkey).parseClaimsJws(token).getBody();
        Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get("authority").toString().split(",")).map(r -> new SimpleGrantedAuthority(r)).collect(Collectors.toList());
        Authentication authentication = new UsernamePasswordAuthenticationToken(email, "", authorities);
        return authentication;
    }
}
