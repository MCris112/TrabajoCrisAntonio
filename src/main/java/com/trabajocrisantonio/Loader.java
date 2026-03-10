/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.trabajocrisantonio;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 *
 * @author crisv
 */
public class Loader extends javax.swing.JFrame {

    private JLabel imageLabel;

    /**
     * Creates new form Loader
     */
    public Loader() {
        setUndecorated(true); // Estilo de Splash Screen
        setSize(400, 400);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BorderLayout()); // Más simple para una imagen centrada

        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);

        // Cargar el GIF
        URL url = getClass().getResource("/loader.gif");
        if (url != null) {
            ImageIcon icon = new ImageIcon(url);
            // Si el GIF ya tiene el tamaño adecuado o queremos que se escale
            // Para GIFs animados, escalar con getScaledInstance puede romper la animación.
            // Es mejor mostrar el ImageIcon directamente si es un GIF animado.
            imageLabel.setIcon(icon);
        } else {
            imageLabel.setText("Cargando...");
        }

        panel.add(imageLabel, BorderLayout.CENTER);
        setContentPane(panel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Se puede agregar lógica adicional aquí para el cargador si se desea.
}
