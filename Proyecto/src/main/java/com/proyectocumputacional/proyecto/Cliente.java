package com.proyectocumputacional.proyecto;

import java.util.HashMap;
import java.util.Map;

public class Cliente {
    private String nombre;
    private String correo;
    private String telefono;
    private Map<String, Object> attributes;

    protected Cliente(Builder builder) {
        this.nombre = builder.nombre;
        this.correo = builder.correo;
        this.telefono = builder.telefono;
        this.attributes = builder.attributes;
    }

    public static class Builder {
        private String nombre;
        private String correo;
        private String telefono;
        private Map<String, Object> attributes = new HashMap<>();

        public Builder setNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder setCorreo(String correo) {
            this.correo = correo;
            return this;
        }

        public Builder setTelefono(String telefono) {
            this.telefono = telefono;
            return this;
        }

        public Builder setAttribute(String key, Object value) {
            attributes.put(key, value);
            return this;
        }

        public Builder setBeneficios(String beneficios) {
            setAttribute("beneficios", beneficios);  // Agregar beneficios como un atributo
            return this;
        }

        public Cliente build() {
            return new Cliente(this);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public Object getAttribute(String key) {
        return attributes.get(key);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", attributes=" + attributes +
                '}';
    }
}
