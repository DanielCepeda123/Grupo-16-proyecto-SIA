package com.proyectocumputacional.proyecto;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;


public class Proyecto {
    private static final ServicioTecnico servicio = new ServicioTecnico();
    private static final InterfazProyecto interfazProyecto = new InterfazProyecto();
    private static int numeroOrden = 0;
    private static List<JButton> menuButtons;
    private static int numeroOrdenSeleccionada;

    
    public static void main(String[] args) {
        // Inicializar menuButtons
        menuButtons = new ArrayList<>();

        // Mostrar la interfaz gráfica
        javax.swing.SwingUtilities.invokeLater(() -> {
            interfazProyecto.mostrarInterfaz();
            establecerManejadoresEventosMenu(); // Configurar manejadores de eventos para los botones del menú
        });
    }

    
    public static void establecerManejadoresEventosMenu() {
        for (JButton button : interfazProyecto.getMenuButtons()) {
            button.addActionListener(e -> {
                String buttonText = ((JButton) e.getSource()).getText();
                ejecutarMenu(buttonText);
            });
        }
    }
    
    private static void ejecutarMenu(String opcionSeleccionada) {
    switch (opcionSeleccionada) {
        case "Agregar Nueva Orden de Trabajo":
            try {
                agregarOrden(interfazProyecto);
            } catch (ProyectoException.OrdenInvalidaException e) {
                interfazProyecto.mostrarMensaje(e.getMessage());
            }
            break;
        case "Actualizar Orden de Trabajo":
            try {
                actualizarOrden(interfazProyecto);
            } catch (ProyectoException.OrdenInvalidaException | ProyectoException.ClienteNoEncontradoException e) {
                interfazProyecto.mostrarMensaje(e.getMessage());
            }
            break;
        case "Mostrar Todas las Órdenes de Trabajo":
            mostrarOrdenes();
            break;
        case "Salir":
            interfazProyecto.mostrarMensaje("Saliendo...");
            System.exit(0);
            break;
        default:
            interfazProyecto.mostrarMensaje("Opción no válida. Intente nuevamente.");
    }
}


    public static void agregarOrden(InterfazProyecto interfazProyecto) throws ProyectoException.OrdenInvalidaException {
        String clienteNombre = "Pepito";
        String clienteCorreo = "correoDePepito@cliente.com";
        String clienteTelefono = "+56912345678";
        boolean esVIP = true; // Indicar que el cliente es VIP

        Cliente nuevoCliente;
        if (esVIP) {
            nuevoCliente = new ClienteVIP.Builder() // Usar la clase ClienteVIP
                    .setNombre(clienteNombre)
                    .setCorreo(clienteCorreo)
                    .setTelefono(clienteTelefono)
                    .setBeneficios("Pepito tiene el beneficio de ser bello, más un 10% de descuento en la factura") // Agregar beneficios VIP
                    .build();
        } else {
            nuevoCliente = new Cliente.Builder()
                    .setNombre(clienteNombre)
                    .setCorreo(clienteCorreo)
                    .setTelefono(clienteTelefono)
                    .build();
        }

        String problema = "Pepito tiene el problema de que su computadora no enciende"; // Problema reportado predefinido
        String fecha = "2024-04-28"; // Fecha de recepción predefinida

        int numeroOrden = 0; // Establecer el número de orden temporalmente

        OrdenTrabajo nuevaOrden;
        boolean esUrgente = true;

        if (esUrgente) {
            nuevaOrden = new OrdenTrabajoUrgente(numeroOrden, problema, fecha, "Pendiente", true);
            String tiempoFinalizacion = "1 día"; // Tiempo de finalización estimado predefinido
            interfazProyecto.mostrarMensaje("Tiempo de finalización estimado: " + tiempoFinalizacion);

            // Guardar el tiempo de finalización estimado en el cliente
            nuevoCliente.setTiempoFinalizacion(tiempoFinalizacion);

            // Establecer el valor de esUrgente en la instancia de OrdenTrabajoUrgente
            ((OrdenTrabajoUrgente) nuevaOrden).setEsUrgente(true);
        } else {
            nuevaOrden = OrdenTrabajo.crearOrden(problema, fecha, "Pendiente");
            nuevaOrden.idOrden = numeroOrden;

            // Si no es urgente, establecer el tiempo de finalización en 3 semanas
            nuevoCliente.setTiempoFinalizacion("3 semanas");
        }

        nuevaOrden.setCliente(nuevoCliente);

        servicio.agregarOrden(nuevaOrden);

        interfazProyecto.mostrarMensaje("Orden de trabajo agregada correctamente. Número de orden: " + numeroOrden);
        
        // Mostrar los datos en una ventana
        VentanaOrdenTrabajo ventanaOrden = new VentanaOrdenTrabajo(clienteNombre, clienteCorreo, clienteTelefono, esVIP, esUrgente, problema, fecha);
    }

    
    public static void actualizarOrden(InterfazProyecto interfazProyecto) throws ProyectoException.OrdenInvalidaException, ProyectoException.ClienteNoEncontradoException {
        try {
            InterfazProyecto interfaz = new InterfazProyecto(); 

            // Pedir al usuario que ingrese el número de orden
            String numeroOrdenInput = JOptionPane.showInputDialog(interfaz, "Ingrese el número de orden que desea actualizar:");

            // Convertir la entrada en un número entero
            int numeroOrden = Integer.parseInt(numeroOrdenInput);

            // Almacenar el número de orden seleccionado
            numeroOrdenSeleccionada = numeroOrden;

            OrdenTrabajo ordenEncontrada = null;

            // Buscar la orden de trabajo por su número de orden
            for (OrdenTrabajo orden : servicio.getOrdenes()) {
                if (orden.idOrden == numeroOrden) {
                    ordenEncontrada = orden;
                    break;
                }
            }

            if (ordenEncontrada == null) {
                throw new ProyectoException.OrdenInvalidaException("Número de orden no válido.");
            }

            if (ordenEncontrada.getCliente() == null) {
                throw new ProyectoException.ClienteNoEncontradoException("Cliente no encontrado para la orden " + numeroOrden);
            }

            // Solicitar y actualizar el estado de la orden
            String nuevoEstado = JOptionPane.showInputDialog(interfaz, "Ingrese el nuevo estado de la orden (En proceso, Completado):");
            ordenEncontrada.setEstado(nuevoEstado);

            servicio.actualizarOrden(ordenEncontrada);

            interfaz.mostrarMensaje("Orden de trabajo actualizada correctamente.");
        } catch (ProyectoException.OrdenInvalidaException | ProyectoException.ClienteNoEncontradoException e) {
            System.out.println(e.getMessage()); // Imprimir el mensaje de error
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingrese un número válido para el número de orden.");
        }
    }



    public static void mostrarOrdenes() {
        StringBuilder detallesOrdenes = new StringBuilder();

        List<OrdenTrabajo> ordenes = servicio.getOrdenes();

        if (ordenes.isEmpty()) {
            detallesOrdenes.append("No hay órdenes de trabajo registradas.");
        } else {
            detallesOrdenes.append("Listado de órdenes de trabajo:\n\n");

            for (OrdenTrabajo orden : ordenes) {
                detallesOrdenes.append("Número de orden: ").append(orden.idOrden).append("\n");
                detallesOrdenes.append("Cliente: ").append(orden.getCliente().getNombre()).append("\n");
                detallesOrdenes.append("Correo del cliente: ").append(orden.getCliente().getCorreo()).append("\n");
                detallesOrdenes.append("Teléfono del cliente: ").append(orden.getCliente().getTelefono()).append("\n");

                if (orden.getCliente() instanceof ClienteVIP) {
                    detallesOrdenes.append("Beneficios VIP: ").append(((ClienteVIP) orden.getCliente()).getBeneficios()).append("\n");
                }

                detallesOrdenes.append("Problema: ").append(orden.problema).append("\n");
                detallesOrdenes.append("Fecha de recepción: ").append(orden.fecha).append("\n");
                detallesOrdenes.append("Estado: ").append(orden.getEstado()).append("\n");

                // Obtener el tiempo de finalización estimado
                String tiempoFinalizacion = orden.getCliente().getTiempoFinalizacion();
                detallesOrdenes.append("Tiempo de finalización estimado: ").append(tiempoFinalizacion).append("\n\n");
            }
        }

        VentanaListadoOrdenes ventanaOrdenes = new VentanaListadoOrdenes(detallesOrdenes.toString());
    }
}
