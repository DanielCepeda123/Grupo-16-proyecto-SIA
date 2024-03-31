package com.proyectocumputacional.proyecto;

import java.util.List;
import java.util.Scanner;

public class Proyecto {
    private static final ServicioTecnico servicio = new ServicioTecnico();
    private static final Scanner scanner = new Scanner(System.in);
    private static int numeroOrden = 0;

    public static void main(String[] args) {
        try (scanner) {
            int opcion;

            do {
                System.out.println("----------------------------------");
                System.out.println("Menú:");
                System.out.println("1) Agregar Nueva Orden de Trabajo");
                System.out.println("2) Actualizar Orden de Trabajo");
                System.out.println("3) Mostrar Todas las Órdenes de Trabajo");
                System.out.println("4) Salir");
                System.out.print("Seleccione una opción: ");

                opcion = scanner.nextInt();
                scanner.nextLine();  // Consumir el salto de línea
                System.out.println("----------------------------------");

                switch (opcion) {
                    case 1 -> agregarOrden();
                    case 2 -> actualizarOrden();
                    case 3 -> mostrarOrdenes();
                    case 4 -> System.out.println("Saliendo...");
                    default -> System.out.println("Opción no válida. Intente nuevamente.");
                }

            } while (opcion != 4);
        }
    }

    public static void agregarOrden() {
        System.out.print("Ingrese el nombre del cliente: ");
        String clienteNombre = scanner.nextLine();

        System.out.print("Ingrese el problema reportado: ");
        String problema = scanner.nextLine();

        System.out.print("Ingrese la fecha de recepción (YYYY-MM-DD): ");
        String fecha = scanner.nextLine();

        numeroOrden++;  // Incrementar el número de orden

        // Crear una nueva orden con el número de orden, problema, fecha y estado "Pendiente"
        OrdenTrabajo nuevaOrden = new OrdenTrabajo();
        nuevaOrden.setOrden(numeroOrden, problema, fecha, "Pendiente");

        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setNombre(clienteNombre);
        nuevaOrden.setCliente(nuevoCliente);

        servicio.agregarOrden(nuevaOrden);

        System.out.println("Orden de trabajo agregada correctamente. Número de orden: " + numeroOrden);
    }


    public static void actualizarOrden() {
        System.out.print("Ingrese el número de orden que desea actualizar: ");
        int numeroOrden = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea

        OrdenTrabajo ordenEncontrada = null;

        // Buscar la orden de trabajo por su número de orden
        for (OrdenTrabajo orden : servicio.getOrdenes()) {
            if (orden.idOrden == numeroOrden) {
                ordenEncontrada = orden;
                break;
            }
        }

        if (ordenEncontrada != null) {
            // Mostrar los detalles de la orden encontrada
            System.out.println("Detalles de la orden de trabajo:");
            System.out.println("Número de orden: " + ordenEncontrada.idOrden);
            System.out.println("Cliente: " + ordenEncontrada.getCliente().getNombre());
            System.out.println("Problema: " + ordenEncontrada.problema);
            System.out.println("Fecha de recepción: " + ordenEncontrada.fecha);
            System.out.println("Estado: " + ordenEncontrada.estado);

            // Solicitar y actualizar el estado de la orden
            System.out.print("Ingrese el nuevo estado de la orden (En proceso, Completado): ");
            String nuevoEstado = scanner.nextLine();
            ordenEncontrada.setEstado(nuevoEstado);

            servicio.actualizarOrden(ordenEncontrada);

            System.out.println("Estado de la orden actualizado correctamente.");
        } else {
            System.out.println("Número de orden no válido.");
        }
    }


    public static void mostrarOrdenes() {
        List<OrdenTrabajo> ordenes = servicio.getOrdenes();

        if (ordenes.isEmpty()) {
            System.out.println("No hay órdenes de trabajo registradas.");
            return;
        }

        System.out.println("Listado de órdenes de trabajo:");

        for (int i = 0; i < ordenes.size(); i++) {
            OrdenTrabajo orden = ordenes.get(i);

            System.out.println("----------------------------------");
            System.out.println("Número de orden: " + orden.idOrden);
            System.out.println("Cliente: " + orden.getCliente().getNombre());
            System.out.println("Problema: " + orden.problema);
            System.out.println("Fecha de recepción: " + orden.fecha);
            System.out.println("Estado: " + orden.estado);
        }
    }

}