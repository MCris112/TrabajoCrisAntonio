package com.trabajocrisantonio.Views.Admin;

import com.trabajocrisantonio.Style;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LibroVista extends JPanel {
    public JButton btnInsertar = new JButton("Insertar");
    public JButton btnActualizar = new JButton("Actualizar");
    public JButton btnBorrar = new JButton("Borrar");
    public JButton btnLimpiar = new JButton("Limpiar");

    public JTextField fieldidLibro = new JTextField(6);
    public JTextField fieldEditorial = new JTextField(6);
    public JTextField fieldNumerohojas = new JTextField(6);
    public JTextField fieldTitulo = new JTextField(10);
    public JTextField fieldAutor = new JTextField(10);
    public JTextField fieldGenero = new JTextField(6);
    public JTextField fieldPrecio = new JTextField(6);
    public JTextField fieldBestseller = new JTextField(6);


    public DefaultTableModel modeloTabla = new DefaultTableModel(new Object[]{"idLibro", "editorial", "numeroHojas", "titulo", "autor", "género", "precio", "bestseller"}, 0);

    public JTable table = new JTable(modeloTabla);


    JPanel panelFormulario = new JPanel();

    public LibroVista() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos Libro"));

        Style.tabla(table);
        // Panel donde se van a ver todos los botones creados
        JPanel panelBtn = new JPanel();

        Style.button(btnInsertar, new Color(33, 150, 243));
        Style.button(btnBorrar, new Color(244, 67, 54));
        Style.button(btnActualizar, new Color(76, 175, 80));
        Style.button(btnLimpiar, new Color(158, 158, 158));

        panelBtn.add(btnInsertar);
        panelBtn.add(btnBorrar);
        panelBtn.add(btnActualizar);
        panelBtn.add(btnLimpiar);

        //Contenedor donde se ven los datos de los libros
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createTitledBorder("Listado libros"));

        panelFormulario.setLayout(new GridLayout(1, 0));

        //Linea datos usuario, los campos que pertenecen a usuario
        panelFormulario.add(new JLabel("idLibro"));
        panelFormulario.add(fieldidLibro);

        panelFormulario.add(new JLabel("editorial"));
        panelFormulario.add(fieldEditorial);

        panelFormulario.add(new JLabel("numeroHojas"));
        panelFormulario.add(fieldNumerohojas);

        panelFormulario.add(new JLabel("titulo"));
        panelFormulario.add(fieldTitulo);

        panelFormulario.add(new JLabel("autor"));
        panelFormulario.add(fieldAutor);

        panelFormulario.add(new JLabel("genero"));
        panelFormulario.add(fieldGenero);

        panelFormulario.add(new JLabel("precio"));
        panelFormulario.add(fieldPrecio);

        panelFormulario.add(new JLabel("bestseller"));
        panelFormulario.add(fieldBestseller);

        add(panelBtn);
        add(panelFormulario);
        add(scroll);

    }
}
