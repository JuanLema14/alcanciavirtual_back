package com.alcanciavirtual.alcanciavirtual_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alcanciavirtual.alcanciavirtual_back.model.Usuario;


@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
}

