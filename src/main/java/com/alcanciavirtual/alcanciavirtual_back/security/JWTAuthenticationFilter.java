package com.alcanciavirtual.alcanciavirtual_back.security;

import com.alcanciavirtual.alcanciavirtual_back.exepciones.NokResponseException;
import com.alcanciavirtual.alcanciavirtual_back.model.ResponseData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException {

        AuthCredentials authCredentials = new AuthCredentials();

        try {
            authCredentials = new ObjectMapper().readValue(request.getReader(), AuthCredentials.class);
        } catch (IOException e) {
            throw new NokResponseException("Error al leer las credenciales");
        }

        UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(
                authCredentials.getEmail(), authCredentials.getPassword(), Collections.emptyList());

        return getAuthenticationManager().authenticate(usernamePAT);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
            HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {

        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
        String token = TokenUtils.generateJWTToken(userDetails.getNombre(), userDetails.getUsername());

        ObjectMapper objectMapper = new ObjectMapper();
        ResponseData responseData = new ResponseData("OK", "Ejecución satisfactoria", userDetails.getUsername(),
                userDetails.getNombre());
        String jsonResponse = objectMapper.writeValueAsString(responseData);
        response.addHeader("Authorization", "Bearer " + token);
        response.setContentType("application/json");
        response.getWriter().write(jsonResponse);
        response.getWriter().flush();

        super.successfulAuthentication(request, response, chain, authResult);
    }
}