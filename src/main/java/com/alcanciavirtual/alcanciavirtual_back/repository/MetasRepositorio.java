package com.alcanciavirtual.alcanciavirtual_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

import com.alcanciavirtual.alcanciavirtual_back.model.Metas;

@Repository
public interface MetasRepositorio extends JpaRepository<Metas, Integer> {
    Optional<Metas> findById(Integer id);

    Page<Metas> findAll(Pageable pageable);

    void deleteById(Integer id);

    @Query("SELECT m FROM Metas m WHERE m.id_usuario.id = :userId")
    Page<Metas> findByIdUsuario(Integer userId, Pageable pageable);


}
