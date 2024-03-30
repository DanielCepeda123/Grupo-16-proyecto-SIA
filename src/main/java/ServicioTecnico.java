package com.proyectocumputacional.proyecto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServicioTecnico {
    private List<OrdenTrabajo> ordenes = new ArrayList<>();
    private HashMap<String, ArrayList<OrdenTrabajo>> mapaOrdenesPorEstado = new HashMap<>();

    // Método para agregar una nueva orden de trabajo
    public void agregarOrden(OrdenTrabajo orden) {
        ordenes.add(orden);

        // Actualizar mapaOrdenesPorEstado
        String estado = orden.getEstado();
        if (mapaOrdenesPorEstado.containsKey(estado)) {
            mapaOrdenesPorEstado.get(estado).add(orden);
        } else {
            ArrayList<OrdenTrabajo> nuevaLista = new ArrayList<>();
            nuevaLista.add(orden);
            mapaOrdenesPorEstado.put(estado, nuevaLista);
        }
    }

    // Método para actualizar una orden de trabajo existente
    public void actualizarOrden(OrdenTrabajo ordenActualizada) {
        for (int i = 0; i < ordenes.size(); i++) {
            if (ordenes.get(i).idOrden == ordenActualizada.idOrden) {
                ordenes.set(i, ordenActualizada);

                // Actualizar mapaOrdenesPorEstado
                String estado = ordenActualizada.getEstado();
                if (mapaOrdenesPorEstado.containsKey(estado)) {
                    mapaOrdenesPorEstado.get(estado).set(i, ordenActualizada);
                }
                break;
            }
        }
    }

    // Método para obtener todas las órdenes de trabajo
    public List<OrdenTrabajo> getOrdenes() {
        return ordenes;
    }

    // Método para mostrar órdenes de trabajo por estado
    public void mostrarOrdenesPorEstado(String estado) {
        if (mapaOrdenesPorEstado.containsKey(estado)) {
            ArrayList<OrdenTrabajo> ordenesEstado = mapaOrdenesPorEstado.get(estado);
            for (OrdenTrabajo orden : ordenesEstado) {
                System.out.println(orden);
            }
        } else {
            System.out.println("No hay órdenes de trabajo con el estado: " + estado);
        }
    }
}
