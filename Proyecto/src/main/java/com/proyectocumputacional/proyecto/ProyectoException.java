package com.proyectocumputacional.proyecto;

public class ProyectoException {

    public static class OrdenInvalidaException extends Exception {
        public OrdenInvalidaException(String mensaje) {
            super(mensaje);
        }
    }

    public static class ClienteNoEncontradoException extends Exception {
        public ClienteNoEncontradoException(String mensaje) {
            super(mensaje);
        }
    }
}
