package com.trabajocrisantonio.Views.Admin;

import com.trabajocrisantonio.Componentes.MatComboBox;
import com.trabajocrisantonio.Componentes.MatTextField;
import com.trabajocrisantonio.Style;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PrestamoVista extends JPanel {
    public JButton btnInsertar = new JButton("Insertar");
    public JButton btnActualizar = new JButton("Actualizar");
    public JButton btnBorrar = new JButton("Borrar");
    public JButton btnLimpiar = new JButton("Limpiar");

    public MatTextField fieldid = new MatTextField();
    public MatTextField fieldidLibro = new MatTextField();
    public MatTextField fieldNif = new MatTextField();
    public MatTextField fieldFechaInicio = new MatTextField();
    public MatTextField fieldFechaFin = new MatTextField();
    public MatComboBox<String> fieldDevuelto = new MatComboBox<>(new String[] { "SI", "NO" });

    public DefaultTableModel modeloTabla = new DefaultTableModel(
            new Object[] { "ID", "ID Libro", "NIF Usuario", "Fecha Inicio", "Fecha Fin", "Devuelto" }, 0);
    public JTable table = new JTable(modeloTabla);

    public PrestamoVista() {
        setLayout(new BorderLayout(20, 20));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 1. Panel Superior (Botones y Formulario)
        JPanel topContainer = new JPanel(new BorderLayout(10, 20));
        topContainer.setBackground(Color.WHITE);

        // --- Botones (CRUD) ---
        JPanel panelBtn = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelBtn.setBackground(Color.WHITE);

        Style.button(btnInsertar, new Color(33, 150, 243)); // Material Blue
        Style.button(btnBorrar, new Color(244, 67, 54));    // Material Red
        Style.button(btnActualizar, new Color(76, 175, 80)); // Material Green
        Style.button(btnLimpiar, new Color(158, 158, 158)); // Material Grey

        panelBtn.add(btnInsertar);
        panelBtn.add(btnBorrar);
        panelBtn.add(btnActualizar);
        panelBtn.add(btnLimpiar);

        // --- Formulario (GridBagLayout para precisión) ---
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(Color.WHITE);
        panelFormulario.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230)), "DATOS DEL PRÉSTAMO"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 1: ID Préstamo e ID Libro
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        panelFormulario.add(new JLabel("ID Préstamo"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        panelFormulario.add(fieldid, gbc);

        gbc.gridx = 2; gbc.weightx = 0;
        panelFormulario.add(new JLabel("ID Libro"), gbc);
        gbc.gridx = 3; gbc.weightx = 1.0;
        panelFormulario.add(fieldidLibro, gbc);

        // Fila 2: NIF Usuario y Estado (Devuelto)
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        panelFormulario.add(new JLabel("NIF Usuario"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        panelFormulario.add(fieldNif, gbc);

        gbc.gridx = 2; gbc.weightx = 0;
        panelFormulario.add(new JLabel("¿Devuelto?"), gbc);
        gbc.gridx = 3; gbc.weightx = 1.0;
        panelFormulario.add(fieldDevuelto, gbc);

        // Fila 3: Fecha Inicio y Fecha Fin
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        panelFormulario.add(new JLabel("Fecha Inicio"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        panelFormulario.add(fieldFechaInicio, gbc);

        gbc.gridx = 2; gbc.weightx = 0;
        panelFormulario.add(new JLabel("Fecha Fin"), gbc);
        gbc.gridx = 3; gbc.weightx = 1.0;
        panelFormulario.add(fieldFechaFin, gbc);

        // Ensamblar container superior
        topContainer.add(panelBtn, BorderLayout.NORTH);
        topContainer.add(panelFormulario, BorderLayout.CENTER);

        // 2. Tabla (Centro)
        Style.tabla(table);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230)), "LISTADO DE PRÉSTAMOS"));
        scroll.getViewport().setBackground(Color.WHITE);

        add(topContainer, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }
}
