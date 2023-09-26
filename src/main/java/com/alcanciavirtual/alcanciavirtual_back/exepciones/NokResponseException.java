package com.alcanciavirtual.alcanciavirtual_back.exepciones;

public class NokResponseException extends RuntimeException {
    private final String mensaje;

    public NokResponseException() {
        this.mensaje = "Error en la ejecución";
    }

    public NokResponseException(String mensaje) {
        super(mensaje);
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
}
