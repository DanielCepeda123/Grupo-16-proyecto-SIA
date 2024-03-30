package com.proyectocumputacional.proyecto;

public class Cliente {
    private String nombre;
    private String correo;
    private String telefono;

    // Setter sobrecargado para establecer el nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Setter sobrecargado para establecer el nombre y el correo
    public void setNombre(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
    }

    // Setter sobrecargado para establecer el nombre, el correo y el teléfono
    public void setNombre(String nombre, String correo, String telefono) {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
    }

    // Setter para establecer el correo
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    // Setter para establecer el teléfono
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // Getter para obtener el nombre
    public String getNombre() {
        return nombre;
    }

    // Getter para obtener el correo
    public String getCorreo() {
        return correo;
    }

    // Getter para obtener el teléfono
    public String getTelefono() {
        return telefono;
    }

    // Método toString para mostrar la información del cliente
    @Override //Sobreescribiendo un metodo de la superclase
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}