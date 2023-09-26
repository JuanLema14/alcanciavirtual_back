package com.alcanciavirtual.alcanciavirtual_back.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alcanciavirtual.alcanciavirtual_back.model.Usuario;
import com.alcanciavirtual.alcanciavirtual_back.repository.UsuarioRepositorio;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio
                .findOneByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario con email " + email + " no existe."));

        return new UserDetailsImpl(usuario);
    }
}
