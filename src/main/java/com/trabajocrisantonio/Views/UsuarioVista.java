package com.trabajocrisantonio.Views;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
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

    panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos Categoría"));

    add(panelFormulario);
    }

    

}
