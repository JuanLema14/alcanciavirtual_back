package com.alcanciavirtual.alcanciavirtual_back.model;

public class Ejecucion {
    private String estado;
    private String mensaje;

    public Ejecucion() {
        // Constructor sin argumentos necesario para deserialización JSON
    }

    public Ejecucion(String estado, String mensaje) {
        this.estado = estado;
        this.mensaje = mensaje;
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
}

