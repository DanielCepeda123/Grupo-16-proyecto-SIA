package com.proyectocumputacional.proyecto;

public class OrdenTrabajoUrgente extends OrdenTrabajo {
    private boolean esUrgente;

    public OrdenTrabajoUrgente(int idOrden, String problema, String fecha, String estado, boolean esUrgente) {
        super(idOrden, problema, fecha, estado);
        this.esUrgente = esUrgente;
    }

    public void setEsUrgente(boolean esUrgente) {
        this.esUrgente = esUrgente;
    }
    
    @Override
    public boolean esUrgente() {
        return true;  // Una OrdenTrabajoUrgente siempre es urgente
    }

    @Override
    public String toString() {
        return super.toString() + ", esUrgente=" + esUrgente;
    }
}
