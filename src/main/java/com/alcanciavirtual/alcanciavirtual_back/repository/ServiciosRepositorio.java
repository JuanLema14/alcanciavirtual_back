package com.alcanciavirtual.alcanciavirtual_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alcanciavirtual.alcanciavirtual_back.model.Servicios;

@Repository
public interface ServiciosRepositorio extends JpaRepository<Servicios, Integer> {
    Optional<Servicios> findById(Integer id);

    Page<Servicios> findAll(Pageable pageable);

    void deleteById(Integer id);

    @Query("SELECT s FROM Servicios s WHERE s.id_usuario.id = :userId")
    Page<Servicios> findByIdUsuario(Integer userId, Pageable pageable);

}
