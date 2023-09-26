package com.alcanciavirtual.alcanciavirtual_back.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "email", length = 80, nullable = false, unique = true)
    private String email;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "last_seen_at", nullable = true)
    private Long last_seen_at;

    public Usuario() {

    }

    public Usuario(long id, String nombre, String email, String password, Long last_seen_at) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.last_seen_at = last_seen_at;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getLast_seen_at() {
        return this.last_seen_at;
    }

    public void setLast_seen_at(Long last_seen_at) {
        this.last_seen_at = last_seen_at;
    }

}
