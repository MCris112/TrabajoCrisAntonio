package com.trabajocrisantonio.Controllers;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.darkredgm.querymc.Collections.MCList;
import com.darkredgm.querymc.Database.ORM.QueryBuilder;
import com.trabajocrisantonio.Views.LibroVista;
import com.trabajocrisantonio.Views.UsuarioVista;
import com.trabajocrisantonio.modelos.Libro;
import com.trabajocrisantonio.modelos.Usuario;

public class LibroController {
    protected LibroVista vista;

    public LibroController(LibroVista vista) {
        this.vista = vista;

        initController();
        cargarTabla();
    }

    private void cargarTabla() {
        vista.modeloTabla.setRowCount(0);

        try {
            MCList<Libro> listaLibros = QueryBuilder.use(Libro.class).get();
            for (Libro libro : listaLibros) {
                // TODO QueryMC problema
                String[] columna = {String.valueOf(libro.getId_libro()), libro.getEditorial(), String.valueOf(libro.getNumero_hojas()), libro.getTitulo(), libro.getAutor(), libro.getGenero(), String.valueOf(libro.getPrecio()), String.valueOf(libro.isBestseller())};

                vista.modeloTabla.addRow(
                        columna
                );
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vista, e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public void initController() {

        vista.btnInsertar.addActionListener(e -> insertar());
        vista.btnActualizar.addActionListener(e -> actualizar());
        vista.btnLimpiar.addActionListener(e -> limpiar());
        vista.btnBorrar.addActionListener(e -> borrar());

        vista.table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // TODO Auto-generated method stub
                if (!e.getValueIsAdjusting()) {
                    seleccionarFila();
                }
            }
        });

    }

    public void insertar() {
        try {
            Libro libro = new Libro(
                    Integer.parseInt(vista.fieldidLibro.getText()),
                    vista.fieldEditorial.getText(),
                    Integer.parseInt(vista.fieldNumerohojas.getText()),
                    vista.fieldTitulo.getText(),
                    vista.fieldAutor.getText(),
                    vista.fieldGenero.getText(),
                    Integer.parseInt(vista.fieldPrecio.getText()),
                    Boolean.parseBoolean(vista.fieldBestseller.getText()));

            libro.save();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vista, e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }
        cargarTabla();
        limpiar();
    }

    public void actualizar() {
        try {
            QueryBuilder.use(Usuario.class).whereKey(vista.fieldidLibro.getText()).update(builder -> {
                builder.set("id_Libro", vista.fieldidLibro.getText());
                builder.set("editorial", vista.fieldEditorial.getText());
                builder.set("numero hojas", vista.fieldNumerohojas.getText());
                builder.set("titulo", vista.fieldTitulo.getText());
                builder.set("autor", vista.fieldAutor.getText());
                builder.set("genero", vista.fieldGenero.getText());
                builder.set("precio", vista.fieldPrecio.getText());
                builder.set("bestseller", vista.fieldBestseller.getText());
            });
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vista, e.getMessage(), "SQL error", JOptionPane.ERROR_MESSAGE);

        }
        cargarTabla();
        limpiar();
    }

    public void borrar() {
        if (vista.fieldidLibro.getText().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Necesitas seleccionar para borrar");
        } else {
            try {
                QueryBuilder.use(Usuario.class).whereKey(vista.fieldidLibro.getText()).delete();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(vista, e.getMessage(), "SQL error", JOptionPane.ERROR_MESSAGE);
            }
            cargarTabla();
            limpiar();
        }


    }

    public void limpiar() {
        vista.fieldidLibro.setText("");
        vista.fieldEditorial.setText("");
        vista.fieldNumerohojas.setText("");
        vista.fieldTitulo.setText("");
        vista.fieldAutor.setText("");
        vista.fieldGenero.setText("");
        vista.fieldPrecio.setText("");
        vista.fieldBestseller.setText("");

        vista.table.clearSelection();
    }

    public void seleccionarFila() {
        int fila = vista.table.getSelectedRow();
        if (fila < 0) return;

        vista.fieldidLibro.setText((String) vista.modeloTabla.getValueAt(fila, 0));
        vista.fieldEditorial.setText((String) vista.modeloTabla.getValueAt(fila, 1));
        vista.fieldTitulo.setText((String) vista.modeloTabla.getValueAt(fila, 2));
        vista.fieldAutor.setText((String) vista.modeloTabla.getValueAt(fila, 3));
        vista.fieldGenero.setText((String) vista.modeloTabla.getValueAt(fila, 4));
        vista.fieldPrecio.setText((String) vista.modeloTabla.getValueAt(fila, 5));
        vista.fieldBestseller.setText((String) vista.modeloTabla.getValueAt(fila, 6));

    }
}
