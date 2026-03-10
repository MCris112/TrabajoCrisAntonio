package com.trabajocrisantonio.Views;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class UsuarioVista extends JPanel {
    public JButton btnInsertar = new JButton("Insertar");
    public JButton btnActualizar = new JButton("Actualizar");
    public JButton btnBorrar = new JButton("Borrar");
    public JButton btnLimpiar = new JButton("Limpiar");

    public JTextField fieldNif = new JTextField(6);
    public JTextField fieldNombre = new JTextField(6);
    public JTextField fieldApellido = new JTextField(9);
    public JTextField fieldDireccion = new JTextField(6);
    public JTextField fieldTelefono = new JTextField(6);

    public DefaultTableModel modeloTabla = new DefaultTableModel( new Object[]{"nif", "nombre", "apellidos", "direccion", "telefono"}, 0 );

    public JTable table = new JTable(modeloTabla);

    JPanel panelFormulario = new JPanel();
    public UsuarioVista() {
        setLayout(new BorderLayout(10, 10)); // Usar BorderLayout para mejor estructura
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Estilo de la Tabla (Material Design - Blue)
        styleTable();

        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos Usuario"));
        panelFormulario.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Panel donde se van a ver todos los botones creados
        JPanel panelBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
        styleButton(btnInsertar, new Color(33, 150, 243)); // Material Blue
        styleButton(btnBorrar, new Color(244, 67, 54));   // Material Red
        styleButton(btnActualizar, new Color(76, 175, 80)); // Material Green
        styleButton(btnLimpiar, new Color(158, 158, 158)); // Material Grey
        
        panelBtn.add(btnInsertar);
        panelBtn.add(btnBorrar);
        panelBtn.add(btnActualizar);
        panelBtn.add(btnLimpiar);

        // Contenedor donde se ven los datos de los usuarios
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createTitledBorder("Listado usuarios"));
        scroll.getViewport().setBackground(Color.WHITE);

        // Linea datos usuario
        panelFormulario.add(new JLabel("nif"));
        panelFormulario.add(fieldNif);
        panelFormulario.add(new JLabel("nombre"));
        panelFormulario.add(fieldNombre);
        panelFormulario.add(new JLabel("apellidos"));
        panelFormulario.add(fieldApellido);
        panelFormulario.add(new JLabel("telefono"));
        panelFormulario.add(fieldTelefono);
        panelFormulario.add(new JLabel("direccion"));
        panelFormulario.add(fieldDireccion);

        // Organizar componentes
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(panelBtn);
        topPanel.add(panelFormulario);

        add(topPanel, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }

    private void styleTable() {
        table.setRowHeight(35); // Filas más altas (Material Look)
        table.setSelectionBackground(new Color(187, 222, 251)); // Azul claro para selección
        table.setSelectionForeground(Color.BLACK);
        table.setGridColor(new Color(230, 230, 230));
        table.setShowVerticalLines(false); // Solo líneas horizontales
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setBackground(Color.WHITE);

        // Estilo del Header
        JTableHeader header = table.getTableHeader();
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
        
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    private void styleButton(JButton btn, Color color) {
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("SansSerif", Font.BOLD, 12));
        btn.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

}
