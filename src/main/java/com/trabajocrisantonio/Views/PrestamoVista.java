package com.trabajocrisantonio.Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PrestamoVista extends Container {
    public JButton btnInsertar = new JButton("Insertar");
    public JButton btnActualizar = new JButton("Actualizar");
    public JButton btnBorrar = new JButton("Borrar");
    public JButton btnLimpiar = new JButton("Limpiar");

    public JTextField fieldidLibro = new JTextField(6);
    public JTextField fieldnumeroPrestamo = new JTextField(6);
    public JTextField fieldNif = new JTextField(6);
    public JTextField fieldFechaInicio = new JTextField(10);
    public JTextField fieldFechaFin = new JTextField(10);
    public JTextField fieldDevuelto = new JTextField(6);


    public DefaultTableModel modeloTabla = new DefaultTableModel(new Object[]{"idLibro", "numero_prestamo", "nif", "fechaInicio", "fechaFin", "devuelto"}, 0);

    public JTable table = new JTable(modeloTabla);


    JPanel panelFormulario = new JPanel();

    public PrestamoVista() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos prestamos"));

        // Panel donde se van a ver todos los botones creados
        JPanel panelBtn = new JPanel();
        panelBtn.add(btnInsertar);
        panelBtn.add(btnBorrar);
        panelBtn.add(btnActualizar);
        panelBtn.add(btnLimpiar);

        //Contenedor donde se ven los datos de los libros
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createTitledBorder("Listado prestamos"));

        panelFormulario.setLayout(new GridLayout(1, 0));

        //Linea datos usuario, los campos que pertenecen a usuario
        panelFormulario.add(new JLabel("idLibro"));
        panelFormulario.add(fieldidLibro);

        panelFormulario.add(new JLabel("editorial"));
        panelFormulario.add(fieldnumeroPrestamo);

        panelFormulario.add(new JLabel("autor"));
        panelFormulario.add(fieldNif);

        panelFormulario.add(new JLabel("numeroHojas"));
        panelFormulario.add(fieldFechaInicio);

        panelFormulario.add(new JLabel("titulo"));
        panelFormulario.add(fieldFechaFin);

        panelFormulario.add(new JLabel("autor"));
        panelFormulario.add(fieldDevuelto);


        add(panelBtn);
        add(panelFormulario);
        add(scroll);

    }
}
