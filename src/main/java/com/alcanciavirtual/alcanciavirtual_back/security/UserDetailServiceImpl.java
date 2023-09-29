package com.alcanciavirtual.alcanciavirtual_back.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.alcanciavirtual.alcanciavirtual_back.model.Usuario;
import com.alcanciavirtual.alcanciavirtual_back.repository.UsuarioRepositorio;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio
                .findOneByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario con email " + email + " no existe."));

        return new UserDetailsImpl(usuario);
    }

    public Usuario signUp(String nombre, String email, String password) {

        System.out.println(nombre);
        System.out.println(email);
        System.out.println(password);

        Usuario usuarioExistente = usuarioRepositorio.findOneByEmail(email)
                .orElse(null);
        if (usuarioExistente != null) {
            throw new RuntimeException("El usuario con el email " + email + " ya está registrado.");
        }

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setPassword(new BCryptPasswordEncoder().encode(password));

        return usuarioRepositorio.save(nuevoUsuario);
    }
}
