package com.proyectocumputacional.proyecto;

public class OrdenTrabajo {
    public int idOrden;
    public String problema;
    public String fecha;
    public String estado;
    public Cliente cliente;

    // Método para establecer todos los atributos de la orden excepto el cliente
    public void setOrden(int idOrden, String problema, String fecha, String estado) {
        this.idOrden = idOrden;
        this.problema = problema;
        this.fecha = fecha;
        this.estado = estado;
    }

    // Método para establecer el cliente pasando un objeto Cliente como argumento
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    // Método getter para obtener el cliente
    public Cliente getCliente() {
        return cliente;
    }
    
    // Método getter para obtener el estado de la orden
    public String getEstado() {
        return estado;
    }
    
    // Método setter para establecer el estado de la orden
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    // Método estático para crear una nueva OrdenTrabajo
    public static OrdenTrabajo crearOrden(String problema, String fecha, String estado) {
        OrdenTrabajo orden = new OrdenTrabajo();
        orden.setOrden(0, problema, fecha, estado);  // El ID se establece a 0, ya que es un valor temporal
        return orden;
    }
}

