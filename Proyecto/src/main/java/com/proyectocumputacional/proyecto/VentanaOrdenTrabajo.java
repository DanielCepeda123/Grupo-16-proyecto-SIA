package com.proyectocumputacional.proyecto;

import javax.swing.*;
import java.awt.*;

public class VentanaOrdenTrabajo extends JFrame {
    private JTextField txtClienteNombre;
    private JTextField txtClienteCorreo;
    private JTextField txtClienteTelefono;
    private JTextField txtEsVIP;
    private JTextField txtEsUrgente;
    private JTextField txtProblema;
    private JTextField txtFechaRecepcion;

    public VentanaOrdenTrabajo(String clienteNombre, String clienteCorreo, String clienteTelefono,
                           boolean esVIP, boolean esUrgente, String problema, String fechaRecepcion) {
        setTitle("Orden de Trabajo");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 2));

        add(new JLabel("Nombre del Cliente:"));
        txtClienteNombre = new JTextField(clienteNombre);
        txtClienteNombre.setEditable(false);
        add(txtClienteNombre);

        add(new JLabel("Correo del Cliente:"));
        txtClienteCorreo = new JTextField(clienteCorreo);
        txtClienteCorreo.setEditable(false);
        add(txtClienteCorreo);

        add(new JLabel("Número de Teléfono del Cliente:"));
        txtClienteTelefono = new JTextField(clienteTelefono);
        txtClienteTelefono.setEditable(false);
        add(txtClienteTelefono);

        add(new JLabel("¿Es Cliente VIP?:"));
        txtEsVIP = new JTextField(esVIP ? "Sí" : "No");
        txtEsVIP.setEditable(false);
        add(txtEsVIP);

        add(new JLabel("¿Es Urgente?:"));
        txtEsUrgente = new JTextField(esUrgente ? "Sí" : "No");
        txtEsUrgente.setEditable(false);
        add(txtEsUrgente);

        add(new JLabel("Problema:"));
        txtProblema = new JTextField(problema);
        txtProblema.setEditable(false);
        add(txtProblema);

        add(new JLabel("Fecha de Recepción:"));
        txtFechaRecepcion = new JTextField(fechaRecepcion);
        txtFechaRecepcion.setEditable(false);
        add(txtFechaRecepcion);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
