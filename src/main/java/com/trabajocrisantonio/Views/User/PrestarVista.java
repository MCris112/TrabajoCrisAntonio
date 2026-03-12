package com.trabajocrisantonio.Views.User;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

/**
 * Vista para realizar préstamos de libros.
 * Sigue los principios de Material Design.
 */
public class PrestarVista extends JPanel {

    // Componentes de búsqueda
    public JTextField fieldBuscar = new JTextField(20);
    public JButton btnBuscar = new JButton("Buscar Libro");

    // Componentes de resultados
    public DefaultTableModel modeloTabla = new DefaultTableModel(
            new Object[] { "ID", "Título", "Autor", "Estado" }, 0);
    public JTable tableResultados = new JTable(modeloTabla);

    // Botón de acción principal
    public JButton btnPrestar = new JButton("Solicitar Libro Seleccionado");

    public PrestarVista() {
        setLayout(new BorderLayout(15, 15));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // --- Panel de Búsqueda (Arriba) ---
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelBusqueda.setBackground(Color.WHITE);
        panelBusqueda.setBorder(BorderFactory.createTitledBorder("Buscador de Libros"));

        JLabel lblBuscar = new JLabel("Ingrese título o autor:");
        lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 14));

        styleTextField(fieldBuscar);
        styleButton(btnBuscar, new Color(33, 150, 243)); // Material Blue

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

        styleButton(btnPrestar, new Color(76, 175, 80)); // Material Green
        btnPrestar.setFont(new Font("Roboto", Font.BOLD, 14));
        btnPrestar.setPreferredSize(new Dimension(250, 45));

        panelAccion.add(btnPrestar);

        // Añadirlo al panel
        add(panelBusqueda, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelAccion, BorderLayout.SOUTH);
    }

    private void styleTable() {
        tableResultados.setRowHeight(40);
        tableResultados.setSelectionBackground(new Color(225, 245, 254));
        tableResultados.setSelectionForeground(Color.BLACK);
        tableResultados.setShowVerticalLines(false);
        tableResultados.setGridColor(new Color(240, 240, 240));
        tableResultados.setFont(new Font("Roboto", Font.PLAIN, 14));

        // Header de la tabla
        JTableHeader header = tableResultados.getTableHeader();
        header.setFont(new Font("Roboto", Font.BOLD, 14));
        header.setBackground(new Color(33, 150, 243));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(0, 40));

        // Renderizado de celdas
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                JLabel c = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
                        column);
                c.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(252, 252, 252));
                }
                return c;
            }
        };

        for (int i = 0; i < tableResultados.getColumnCount(); i++) {
            tableResultados.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
    }

    private void styleTextField(JTextField field) {
        field.setPreferredSize(new Dimension(250, 35));
        field.setFont(new Font("Roboto", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
    }

    private void styleButton(JButton btn, Color color) {
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setFont(new Font("Roboto", Font.BOLD, 13));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }
}
