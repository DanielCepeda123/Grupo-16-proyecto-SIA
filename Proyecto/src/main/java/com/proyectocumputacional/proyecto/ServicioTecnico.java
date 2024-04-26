package com.proyectocumputacional.proyecto;

import java.util.*;

public class ServicioTecnico {
    private Map<String, Set<OrdenTrabajo>> mapaOrdenesPorEstado = new HashMap<>();

    // Método para agregar una nueva orden de trabajo
    public void agregarOrden(OrdenTrabajo orden) {
        String estado = orden.getEstado();
        mapaOrdenesPorEstado.computeIfAbsent(estado, k -> new HashSet<>()).add(orden);
    }

    // Método para actualizar una orden de trabajo existente
    public void actualizarOrden(OrdenTrabajo ordenActualizada) {
        String estado = ordenActualizada.getEstado();
        if (mapaOrdenesPorEstado.containsKey(estado)) {
            mapaOrdenesPorEstado.get(estado).remove(ordenActualizada);
            mapaOrdenesPorEstado.get(estado).add(ordenActualizada);
        }
    }

    // Método para obtener todas las órdenes de trabajo
    public List<OrdenTrabajo> getOrdenes() {
        List<OrdenTrabajo> ordenes = new ArrayList<>();
        for (Set<OrdenTrabajo> ordenesEstado : mapaOrdenesPorEstado.values()) {
            ordenes.addAll(ordenesEstado);
        }
        return ordenes;
    }

    // Método para mostrar órdenes de trabajo por estado
    public void mostrarOrdenesPorEstado(String estado) {
        if (mapaOrdenesPorEstado.containsKey(estado)) {
            for (OrdenTrabajo orden : mapaOrdenesPorEstado.get(estado)) {
                System.out.println(orden);
            }
        } else {
            System.out.println("No hay órdenes de trabajo con el estado: " + estado);
        }
    }
    
    public void eliminarOrden(int idOrden){
        // Buscar la orden de trabajo por su id.
        for (Set<OrdenTrabajo> ordenesEstado : mapaOrdenesPorEstado.values()) {
            for (OrdenTrabajo orden : ordenesEstado) {
                if (orden.getIdOrden() == idOrden) {
                    // Eliminar la orden encontrada.
                    ordenesEstado.remove(orden);
                    System.out.println("Orden de trabajo " + idOrden + " eliminada correctamente.");
                    return;
                }
            }
        }

        System.out.println("No se encontró una orden de trabajo con el número " + idOrden + ".");
        
    }
}
