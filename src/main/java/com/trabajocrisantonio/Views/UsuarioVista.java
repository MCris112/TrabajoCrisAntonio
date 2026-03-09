package com.trabajocrisantonio.Views;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class UsuarioVista extends JPanel {
    public JButton btnInsertar = new JButton("Insertar");
    public JButton btnActualizar = new JButton("Actualizar");
    public JButton btnBorrar = new JButton("Borrar");
    public JButton btnLimpiar = new JButton("Limpiar");

    public JTextField fieldNif = new JTextField();
    public JTextField fieldNombre = new JTextField();
    public JTextField fieldApellido = new JTextField();
    public JTextField fieldDireccion = new JTextField();
    public JTextField fieldTelefono = new JTextField();

    public DefaultTableModel modeloTabla = new DefaultTableModel( new Object[]{"nif", "nombre", "apellidos", "direccion", "telefono"}, 0 );

    public JTable table = new JTable(modeloTabla);

    JPanel panelFormulario = new JPanel();
    public UsuarioVista(){
    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

    panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos Usuario"));

    JPanel panelBtn = new JPanel();
    panelBtn.add(btnInsertar);
    panelBtn.add(btnBorrar);
    panelBtn.add(btnActualizar);
    panelBtn.add(btnLimpiar);

    JScrollPane scroll = new JScrollPane(table);
    scroll.setBorder(BorderFactory.createTitledBorder("Listado usuarios"));

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

    add(panelBtn);
    add(panelFormulario);
    add(scroll);

    }  

}
