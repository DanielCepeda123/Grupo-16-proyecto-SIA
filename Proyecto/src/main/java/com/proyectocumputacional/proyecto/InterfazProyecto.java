package com.proyectocumputacional.proyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class InterfazProyecto {
    private JTextArea outputTextArea;
    private JTextField inputTextField;
    private List<JButton> menuButtons;


    public void mostrarInterfaz() {
        JFrame frame = new JFrame("Proyecto");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        configurarComponentes(panel); // Llamada al método para configurar los componentes

        frame.setSize(450, 300);
        frame.setVisible(true);
    }

    private void configurarComponentes(JPanel panel) {
        panel.setLayout(new BorderLayout());

        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        inputTextField = new JTextField(); // Aquí debe estar la inicialización
        panel.add(inputTextField, BorderLayout.SOUTH);

        // Configurar el panel de botones del menú
        String[] opcionesMenu = {"Agregar Nueva Orden de Trabajo", "Actualizar Orden de Trabajo", "Mostrar Todas las Órdenes de Trabajo", "Salir"};
        JPanel buttonPanel = new JPanel(new GridLayout(opcionesMenu.length, 1));

        menuButtons = new ArrayList<>();
        for (String opcion : opcionesMenu) {
            JButton button = new JButton(opcion);
            buttonPanel.add(button);
            menuButtons.add(button);
        }

        panel.add(buttonPanel, BorderLayout.WEST);
    }

    // Método para mostrar mensajes en el JTextArea
    public void mostrarMensaje(String mensaje) {
        if (outputTextArea != null) {
            outputTextArea.append(mensaje + "\n"); // Agregar un salto de línea
        } else {
            System.out.println("El objeto outputTextArea es nulo. No se puede mostrar el mensaje.");
        }
    }

    // Método para obtener la entrada del usuario desde el JTextField
    public String obtenerEntrada(String mensaje) {
        // Mostrar el mensaje al usuario
        mostrarMensaje(mensaje);

        // Obtener la entrada del usuario desde el JTextField
        return inputTextField.getText();
    }


    // Método para obtener la lista de botones del menú
    public List<JButton> getMenuButtons() {
        return menuButtons;
    }

    // Método para establecer los manejadores de eventos para los botones del menú
    public void establecerManejadoresEventosMenu(ActionListener listener) {
        for (JButton button : menuButtons) {
            button.addActionListener(listener);
        }
    }
}