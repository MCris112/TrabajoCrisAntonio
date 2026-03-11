package com.trabajocrisantonio.Views.Admin;

import com.trabajocrisantonio.Style;

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

        // Estilo de la Tabla
        Style.tabla(table);

        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos Usuario"));
        panelFormulario.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Panel donde se van a ver todos los botones creados
        JPanel panelBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
        Style.button(btnInsertar, new Color(33, 150, 243));
        Style.button(btnBorrar, new Color(244, 67, 54));
        Style.button(btnActualizar, new Color(76, 175, 80));
        Style.button(btnLimpiar, new Color(158, 158, 158));
        
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

}
