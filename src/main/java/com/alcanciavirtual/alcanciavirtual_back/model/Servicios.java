package com.alcanciavirtual.alcanciavirtual_back.model;

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
import java.time.LocalDateTime;

@Entity
@Table(name = "servicios")
public class Servicios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre_servicio", nullable = false)
    private String nombre_servicio;

    @Column(name = "valor_servicio", nullable = false)
    private Integer valor_servicio;

    @Column(name = "estado", nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoServicio estado;

    @Column(name = "cantidad_meses", nullable = false)
    private Integer cantidad_meses;

    @Column(name = "fecha_pago", nullable = false)
    private LocalDateTime fecha_pago;

    @Column(name = "tipo_servicio", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoServicio tipo_servicio;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario id_usuario;

    public Servicios() {
    }

    public Servicios(String nombre_servicio, Integer valor_servicio, EstadoServicio estado, Integer cantidad_meses, LocalDateTime fecha_pago, TipoServicio tipo_servicio, Usuario id_usuario) {
        this.nombre_servicio = nombre_servicio;
        this.valor_servicio = valor_servicio;
        this.estado = estado;
        this.cantidad_meses = cantidad_meses;
        this.fecha_pago = fecha_pago;
        this.tipo_servicio = tipo_servicio;
        this.id_usuario = id_usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre_servicio() {
        return nombre_servicio;
    }

    public void setNombre_servicio(String nombre_servicio) {
        this.nombre_servicio = nombre_servicio;
    }

    public Integer getValor_servicio() {
        return valor_servicio;
    }

    public void setValor_servicio(Integer valor_servicio) {
        this.valor_servicio = valor_servicio;
    }

    public EstadoServicio getEstado() {
        return estado;
    }

    public void setEstado(EstadoServicio estado) {
        this.estado = estado;
    }

    public Integer getCantidad_meses() {
        return cantidad_meses;
    }

    public void setCantidad_meses(Integer cantidad_meses) {
        this.cantidad_meses = cantidad_meses;
    }

    public LocalDateTime getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(LocalDateTime fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public TipoServicio getTipo_servicio() {
        return tipo_servicio;
    }

    public void setTipo_servicio(TipoServicio tipo_servicio) {
        this.tipo_servicio = tipo_servicio;
    }

    public Usuario getUsuario() {
        return id_usuario;
    }

    public void setUsuario(Usuario id_usuario) {
        this.id_usuario = id_usuario;
    }

}
