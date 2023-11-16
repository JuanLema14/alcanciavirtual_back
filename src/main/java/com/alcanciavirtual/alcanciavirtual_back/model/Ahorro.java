package com.alcanciavirtual.alcanciavirtual_back.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Ahorro")
public class Ahorro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "valor_ahorro", nullable = false)
    private Integer valor_ahorro;

    @Column(name = "tipo_ahorro", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoAhorro tipo_ahorro;

    @Column(name = "fecha_ingreso", nullable = false)
    private LocalDateTime fecha_ingreso;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario id_usuario;

    public Ahorro() {
    }

    public Ahorro(Integer valor_ahorro, TipoAhorro tipo_ahorro, LocalDateTime fecha_ingreso, Usuario id_usuario) {
        this.valor_ahorro = valor_ahorro;
        this.tipo_ahorro = tipo_ahorro;
        this.fecha_ingreso = fecha_ingreso;
        this.id_usuario = id_usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getValorAhorro() {
        return valor_ahorro;
    }

    public void setValorAhorro(Integer valor_ahorro) {
        this.valor_ahorro = valor_ahorro;
    }

    public TipoAhorro getTipoAhorro() {
        return tipo_ahorro;
    }

    public void setTipoAhorro(TipoAhorro tipo_ahorro) {
        this.tipo_ahorro = tipo_ahorro;
    }

    public LocalDateTime getFechaIngreso() {
        return fecha_ingreso;
    }

    public void setFechaIngreso(LocalDateTime fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    @JsonIgnore
    public Usuario getUsuario() {
        return id_usuario;
    }

    public void setUsuario(Usuario id_usuario) {
        this.id_usuario = id_usuario;
    }
}
