package com.alcanciavirtual.alcanciavirtual_back.model;

public class ResponseData_OK {
    private String estado;
    private String mensaje;
    private Object datos;

    public ResponseData_OK(String estado, String mensaje, Object datos) {
        this.estado = estado;
        this.mensaje = mensaje;
        this.datos = datos;
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

    public Object getDatos() {
        return datos;
    }

    public void setDatos(Object datos) {
        this.datos = datos;
    }
    
}


