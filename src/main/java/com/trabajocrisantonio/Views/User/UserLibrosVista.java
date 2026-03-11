package com.trabajocrisantonio.Views.User;

import com.trabajocrisantonio.Style;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

/**
 * Vista para que el usuario visualice sus libros prestados actualmente.
 * Diseño Material Design con una tabla limpia y un botón de devolución.
 */
public class UserLibrosVista extends JPanel {

    // Modelo de tabla para libros prestados
    public DefaultTableModel modeloTabla = new DefaultTableModel(
            new Object[] { "ID Préstamo", "Título del Libro", "Fecha Préstamo", "Fecha Devolución", "Estado" }, 0);
    public JTable tableLibros = new JTable(modeloTabla);

    // Botón para devolver libro (acción principal)
    public JButton btnDevolver = new JButton("Devolver Libro Seleccionado");

    public UserLibrosVista() {
        setLayout(new BorderLayout(15, 15));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // --- Título del Panel ---
        JLabel lblTitulo = new JLabel("Mis Libros Prestados");
        lblTitulo.setFont(new Font("Roboto", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(33, 33, 33));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        // --- Tabla de Libros (Centro) ---
        Style.tabla(tableLibros);
        JScrollPane scrollPane = new JScrollPane(tableLibros);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Historial activo de préstamos"));
        scrollPane.getViewport().setBackground(Color.WHITE);

        // --- Panel de Acciones (Abajo) ---
        JPanel panelAcciones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelAcciones.setBackground(Color.WHITE);

        Style.button(btnDevolver, new Color(33, 150, 243)); // Azul Material
        btnDevolver.setPreferredSize(new Dimension(250, 45));

        panelAcciones.add(btnDevolver);

        // Añadir componentes al panel principal
        add(lblTitulo, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelAcciones, BorderLayout.SOUTH);
    }


}
