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
        return this.esUrgente;
    }
}
