package com.trabajocrisantonio.Views.User;

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
        styleTable();
        JScrollPane scrollPane = new JScrollPane(tableLibros);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Historial activo de préstamos"));
        scrollPane.getViewport().setBackground(Color.WHITE);

        // --- Panel de Acciones (Abajo) ---
        JPanel panelAcciones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelAcciones.setBackground(Color.WHITE);

        styleButton(btnDevolver, new Color(33, 150, 243)); // Azul Material
        btnDevolver.setPreferredSize(new Dimension(250, 45));

        panelAcciones.add(btnDevolver);

        // Añadir componentes al panel principal
        add(lblTitulo, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelAcciones, BorderLayout.SOUTH);
    }

    private void styleTable() {
        tableLibros.setRowHeight(40);
        tableLibros.setSelectionBackground(new Color(232, 245, 233)); // Verde muy claro para selección
        tableLibros.setSelectionForeground(Color.BLACK);
        tableLibros.setShowVerticalLines(false);
        tableLibros.setGridColor(new Color(245, 245, 245));
        tableLibros.setFont(new Font("Roboto", Font.PLAIN, 14));
        tableLibros.setBackground(Color.WHITE);

        // Estilo del Header
        JTableHeader header = tableLibros.getTableHeader();
        header.setFont(new Font("Roboto", Font.BOLD, 14));
        header.setBackground(new Color(33, 150, 243)); // Azul Material
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(0, 40));
        header.setReorderingAllowed(false);

        // Renderizado de celdas
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                JLabel c = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
                        column);
                c.setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 12));
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(250, 250, 250));
                }

                // Color especial para la columna de Estado (índice 4)
                if (column == 4 && value != null) {
                    if (value.toString().equalsIgnoreCase("Pendiente")) {
                        c.setForeground(new Color(255, 152, 0)); // Naranja Material
                    } else if (value.toString().equalsIgnoreCase("Devuelto")) {
                        c.setForeground(new Color(76, 175, 80)); // Verde Material
                    }
                } else {
                    c.setForeground(new Color(66, 66, 66));
                }

                return c;
            }
        };
        cellRenderer.setHorizontalAlignment(SwingConstants.LEFT);

        for (int i = 0; i < tableLibros.getColumnCount(); i++) {
            tableLibros.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
    }

    private void styleButton(JButton btn, Color color) {
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Roboto", Font.BOLD, 14));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
    }
}
