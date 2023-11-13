package com.alcanciavirtual.alcanciavirtual_back.model;

import java.time.LocalDateTime;

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
@Table(name = "Metas")
public class Metas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cantidad_meta", nullable = false)
    private Integer cantidad_meta;

    @Column(name = "cantidad_abonada", nullable = false)
    private Integer cantidad_abonada;

    @Column(name = "estado", nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoMeta estado;

    @Column(name = "descripcion_meta", nullable = false)
    private String descripcion_meta;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario id_usuario;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fecha_creacion;

    @Column(name = "fecha_esperada", nullable = false)
    private LocalDateTime fecha_esperada;

    public Metas(){}

    public Metas(int cantidad_meta, int cantidad_abonada, EstadoMeta estado, String descripcion_meta, Usuario id_usuario, LocalDateTime fecha_creacion, LocalDateTime fecha_esperada) {
        this.cantidad_meta = cantidad_meta;
        this.cantidad_abonada = cantidad_abonada;
        this.estado = estado;
        this.descripcion_meta = descripcion_meta;
        this.id_usuario = id_usuario;
        this.fecha_creacion = fecha_creacion;
        this.fecha_esperada = fecha_esperada;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCantidad_meta() {
        return cantidad_meta;
    }

    public void setCantidad_meta(Integer cantidad_meta) {
        this.cantidad_meta = cantidad_meta;
    }

    public Integer getCantidad_abonada() {
        return cantidad_abonada;
    }

    public void setCantidad_abonada(Integer cantidad_abonada) {
        this.cantidad_abonada = cantidad_abonada;
    }

    public EstadoMeta getEstado() {
        return estado;
    }

    public void setEstado(EstadoMeta estado) {
        this.estado = estado;
    }

    public String getDescripcion_meta() {
        return descripcion_meta;
    }

    public void setDescripcion_meta(String descripcion_meta) {
        this.descripcion_meta = descripcion_meta;
    }

    public Usuario getUsuario() {
        return id_usuario;
    }

    public void setUsuario(Usuario id_usuario) {
        this.id_usuario = id_usuario;
    }

    public LocalDateTime getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDateTime fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public LocalDateTime getFecha_esperada() {
        return fecha_esperada;
    }

    public void setFecha_esperada(LocalDateTime fecha_esperada) {
        this.fecha_esperada = fecha_esperada;
    }
}
