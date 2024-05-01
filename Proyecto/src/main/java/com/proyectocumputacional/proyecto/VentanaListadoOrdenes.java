package com.proyectocumputacional.proyecto;

import javax.swing.*;
import java.awt.*;

public class VentanaListadoOrdenes extends JFrame {
    private JTextArea textoOrdenes;

    public VentanaListadoOrdenes(String texto) {
        setTitle("Listado de Órdenes de Trabajo");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        textoOrdenes = new JTextArea(texto);
        textoOrdenes.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textoOrdenes);

        add(scrollPane, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
