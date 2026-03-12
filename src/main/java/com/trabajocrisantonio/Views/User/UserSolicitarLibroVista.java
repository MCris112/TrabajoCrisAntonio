package com.trabajocrisantonio.Views.User;

import com.trabajocrisantonio.Componentes.MatTextField;
import com.trabajocrisantonio.Style;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

/**
 * Vista para realizar préstamos de libros.
 * Sigue los principios de Material Design.
 */
public class UserSolicitarLibroVista extends JPanel {

    // Componentes de búsqueda
    public MatTextField fieldBuscar = new MatTextField();
    public JButton btnBuscar = new JButton("Buscar Libro");

    // Componentes de resultados
    public DefaultTableModel modeloTabla = new DefaultTableModel(
            new Object[] { "ID", "Título", "Autor", "Estado" }, 0);
    public JTable tableResultados = new JTable(modeloTabla);

    // Botón de acción principal
    public JButton btnPrestar = new JButton("Solicitar Libro Seleccionado");

    public UserSolicitarLibroVista() {
        setLayout(new BorderLayout(15, 15));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // --- Panel de Búsqueda (Arriba) ---
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelBusqueda.setBackground(Color.WHITE);
        panelBusqueda.setBorder(BorderFactory.createTitledBorder("Buscador de Libros"));

        JLabel lblBuscar = new JLabel("Ingrese título o autor:");
        lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 14));

        Style.button(btnBuscar, new Color(33, 150, 243)); // Material Blue

        panelBusqueda.add(lblBuscar);
        panelBusqueda.add(fieldBuscar);
        panelBusqueda.add(btnBuscar);

        // --- Tabla de Resultados (Centro) ---
        styleTable();
        JScrollPane scrollPane = new JScrollPane(tableResultados);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Resultados de la búsqueda"));
        scrollPane.getViewport().setBackground(Color.WHITE);

        // --- Panel de Acción (Abajo) ---
        JPanel panelAccion = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelAccion.setBackground(Color.WHITE);

        Style.button(btnPrestar, new Color(76, 175, 80)); // Material Green
        btnPrestar.setPreferredSize(new Dimension(250, 45));

        panelAccion.add(btnPrestar);

        // Añadirlo al panel
        add(panelBusqueda, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelAccion, BorderLayout.SOUTH);
    }

    private void styleTable() {
        Style.tabla(tableResultados);
    }
}
