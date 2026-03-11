package com.trabajocrisantonio;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class Style {

    /**
     * Dar estilo a la tabla en Material Design
     * @param tabla
     */
    public static void tabla( JTable tabla )
    {
        tabla.setRowHeight(35); // Filas más altas (Material Look)
        tabla.setSelectionBackground(new Color(187, 222, 251)); // Azul claro para selección
        tabla.setSelectionForeground(Color.BLACK);
        tabla.setGridColor(new Color(230, 230, 230));
        tabla.setShowVerticalLines(false); // Solo líneas horizontales
        tabla.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tabla.setBackground(Color.WHITE);

        // Estilo del Header
        JTableHeader header = tabla.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 14));
        header.setBackground(new Color(33, 150, 243)); // Azul Material Principal
        header.setForeground(Color.WHITE);
        header.setReorderingAllowed(false);
        header.setPreferredSize(new Dimension(100, 40));

        // Renderizado de celdas para centrar y padding
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(250, 250, 250));
                }
                return c;
            }
        };
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public static void button( JButton btn, Color color )
    {
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("SansSerif", Font.BOLD, 12));
        btn.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }


}
