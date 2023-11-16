package com.alcanciavirtual.alcanciavirtual_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.alcanciavirtual.alcanciavirtual_back.model.Ahorro;

@Repository
public interface AhorroRepositorio extends JpaRepository<Ahorro, Integer> {
    Optional<Ahorro> findById(Integer id);

    void deleteById(Integer id);

    @Query("SELECT a FROM Ahorro a WHERE a.id_usuario.id = :userId")
    List<Ahorro> findByIdUsuario(Integer userId);

    @Query("SELECT SUM(a.valor_ahorro) FROM Ahorro a WHERE a.id_usuario.id = :userId")
    Integer sumValorAhorroByIdUsuario(Integer userId);
}
