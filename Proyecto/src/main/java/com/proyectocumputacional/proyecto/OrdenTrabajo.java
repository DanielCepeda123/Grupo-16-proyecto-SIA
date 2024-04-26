package com.proyectocumputacional.proyecto;

public class OrdenTrabajo {
    private int idOrden;
    private String problema;
    private String fecha;
    private  String estado;
    private Cliente cliente;

    // Constructor para establecer todos los atributos de la orden excepto el cliente
    public OrdenTrabajo(int idOrden, String problema, String fecha, String estado) {
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
    
    public void setFecha(String fecha){
        this.fecha = fecha;
    }
    
    public String getFecha(){
        return this.fecha;
    }
    
    public void setProblema(String problema){
        this.problema = problema;
    }
    
    public String getProblema(){
        return this.problema;
    }
    
    public void setIdOrden(int idOrden){
        this.idOrden = idOrden;
    }
    
    public int getIdOrden(){
        return this.idOrden;
    }
    
    // Constructor estático para crear una nueva OrdenTrabajo
    public static OrdenTrabajo crearOrden(String problema, String fecha, String estado) {
        return new OrdenTrabajo(0, problema, fecha, estado);  // El ID se establece a 0, ya que es un valor temporal
    }
    
    public boolean esUrgente() {   
        return false; // Por defecto
    }   
}
