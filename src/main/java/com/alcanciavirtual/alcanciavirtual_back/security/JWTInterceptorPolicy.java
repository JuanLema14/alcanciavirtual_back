package com.alcanciavirtual.alcanciavirtual_back.security;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTInterceptorPolicy implements HandlerInterceptor {
    @Value("${alcancia_dev.jwtSecret}")
    private String jwtSecret;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String authorizationHeader = request.getHeader("Authorization");
        
        System.out.println("validando Token");
        System.out.println(authorizationHeader);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            System.out.println("Token_null");
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "No se proporcionó un token válido");
            return false;
        }

        String token = authorizationHeader.substring(7);

        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token);

            String username = claimsJws.getBody().getSubject();

            return true;
        } catch (ExpiredJwtException ex) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "El token ha expirado");
            return false;
        } catch (SignatureException ex) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "El token no es válido");
            return false;
        } catch (Exception ex) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al procesar el token");
            return false;
        }
    }
}