package com.alcanciavirtual.alcanciavirtual_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.alcanciavirtual.alcanciavirtual_back.model.Usuario;
import com.alcanciavirtual.alcanciavirtual_back.repository.UsuarioRepositorio;

@RestController
@RequestMapping("/api/v1/")
public class UsuarioControler {

    @Autowired
	private UsuarioRepositorio repositorio;

    @GetMapping("/listarusuarios")
    public List<Usuario> listarUsuarios() {
        return repositorio.findAll();
    }
}