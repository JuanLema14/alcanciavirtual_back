package com.alcanciavirtual.alcanciavirtual_back.model;

import java.util.Map;

public class ResponseData {
    private Map<String, Object> respuesta;
    private String estado;
    private String mensaje;
    private Map<String, String> datos;
    private String email;
    private String nombre;

    // Constructor predeterminado
    public ResponseData() {
    }

    // Constructor con cuatro argumentos
    public ResponseData(String estado, String mensaje, String email, String nombre) {
        this.estado = estado;
        this.mensaje = mensaje;
        this.email = email;
        this.nombre = nombre;
    }

    // Getters y setters para todas las propiedades
    // ...

    public Map<String, Object> getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Map<String, Object> respuesta) {
        this.respuesta = respuesta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Map<String, String> getDatos() {
        return datos;
    }

    public void setDatos(Map<String, String> datos) {
        this.datos = datos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}


