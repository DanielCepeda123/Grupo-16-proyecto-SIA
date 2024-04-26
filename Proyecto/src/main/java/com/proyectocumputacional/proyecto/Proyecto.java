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
                System.out.println("Seleccione una opción: ");

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


    public static void agregarOrden() {
        try {
            System.out.println("Ingrese el nombre del cliente: ");
            String clienteNombre = scanner.nextLine();

            System.out.println("Ingrese el correo del cliente: ");
            String clienteCorreo = scanner.nextLine();

            System.out.println("Ingrese el número de teléfono del cliente: ");
            String clienteTelefono = scanner.nextLine();

            System.out.println("¿Es un cliente VIP? (s/n): ");
            String esVIPInput = scanner.nextLine();
            boolean esVIP = esVIPInput.equalsIgnoreCase("s");

            Cliente nuevoCliente;
            if (esVIP) {
                System.out.println("Ingrese los beneficios VIP: ");
                String beneficios = scanner.nextLine();
                nuevoCliente = new Cliente.Builder()
                    .setNombre(clienteNombre)
                    .setCorreo(clienteCorreo)
                    .setTelefono(clienteTelefono)
                    .setBeneficios(beneficios)
                    .build();
            } else {
                nuevoCliente = new Cliente.Builder()
                    .setNombre(clienteNombre)
                    .setCorreo(clienteCorreo)
                    .setTelefono(clienteTelefono)
                    .build();
            }

            System.out.println("Ingrese el problema reportado: ");
            String problema = scanner.nextLine();

            System.out.println("Ingrese la fecha de recepción (YYYY-MM-DD): ");
            String fecha = scanner.nextLine();

            if (problema.isEmpty() || fecha.isEmpty()) {
                throw new OrdenInvalidaException("El problema y la fecha son obligatorios.");
            }

            System.out.println("¿El problema es urgente? (s/n): ");
            String esUrgenteInput = scanner.nextLine();
            boolean esUrgente = esUrgenteInput.equalsIgnoreCase("s");

            numeroOrden++;  // Incrementar el número de orden

            // Crear una nueva orden con el número de orden, problema, fecha y estado "Pendiente"
            OrdenTrabajo nuevaOrden;
            if (esUrgente) {
                nuevaOrden = new OrdenTrabajoUrgente(numeroOrden, problema, fecha, "Pendiente", true);
            } else {
                nuevaOrden = OrdenTrabajo.crearOrden(problema, fecha, "Pendiente");
                nuevaOrden.setIdOrden(numeroOrden);
            }

            nuevaOrden.setCliente(nuevoCliente);

            servicio.agregarOrden(nuevaOrden);

            System.out.println("Orden de trabajo agregada correctamente. Número de orden: " + numeroOrden);
        } catch (OrdenInvalidaException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void actualizarOrden() {
        try {
            System.out.println("Ingrese el número de orden que desea actualizar: ");
            int numeroOrden = scanner.nextInt();
            scanner.nextLine();  // Consumir el salto de línea

            OrdenTrabajo ordenEncontrada = null;

            // Buscar la orden de trabajo por su número de orden
            for (OrdenTrabajo orden : servicio.getOrdenes()) {
                if (orden.getIdOrden() == numeroOrden) {
                    ordenEncontrada = orden;
                    break;
                }
            }

            if (ordenEncontrada == null) {
                throw new OrdenInvalidaException("Número de orden no válido.");
            }

            if (ordenEncontrada.getCliente() == null) {
                throw new ClienteNoEncontradoException("Cliente no encontrado para la orden " + numeroOrden);
            }

            // Solicitar y actualizar el estado de la orden
            System.out.println("Ingrese el nuevo estado de la orden (En proceso, Completado): ");
            String nuevoEstado = scanner.nextLine();
            ordenEncontrada.setEstado(nuevoEstado);

            servicio.actualizarOrden(ordenEncontrada);

            System.out.println("Orden de trabajo actualizada correctamente.");
        } catch (OrdenInvalidaException e) {
            System.out.println(e.getMessage());
        } catch (ClienteNoEncontradoException e) {
            System.out.println(e.getMessage());
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
            System.out.println("Número de orden: " + orden.getIdOrden());
            System.out.println("Cliente: " + orden.getCliente().getNombre());
            System.out.println("Correo del cliente: " + orden.getCliente().getCorreo());
            System.out.println("Teléfono del cliente: " + orden.getCliente().getTelefono());
            if (orden.getCliente() instanceof ClienteVIP) {
                System.out.println("Beneficios VIP: " + ((ClienteVIP) orden.getCliente()).getBeneficios());
            }
            System.out.println("Problema: " + orden.getProblema());
            System.out.println("Fecha de recepción: " + orden.getFecha());
            System.out.println("Estado: " + orden.getEstado());
            System.out.println("Es urgente: " + orden.esUrgente());
        }
    }
}
