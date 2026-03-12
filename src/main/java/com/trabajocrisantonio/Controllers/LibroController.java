package com.trabajocrisantonio.Controllers;

import java.awt.*;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.darkredgm.querymc.Collections.MCList;
import com.darkredgm.querymc.Database.ORM.QueryBuilder;
import com.trabajocrisantonio.Views.Admin.LibroVista;
import com.trabajocrisantonio.modelos.Libro;
import java.net.URL;

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
                String[] columna = {
                        String.valueOf(libro.getId_libro()),
                        libro.getEditorial(),
                        libro.getTitulo(),
                        String.valueOf(libro.getNumero_hojas()),
                        libro.getAutor(),
                        libro.getGenero(),
                        String.valueOf(libro.getPrecio()),
                        decisionBestseller(libro.isBestseller()), // Si | No
                        libro.getImageUrl()
                };

                vista.modeloTabla.addRow(
                        columna);
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
                    esBestSeller(),
                    vista.fieldImagen.getText());

            libro.save();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vista, e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }
        cargarTabla();
        limpiar();
    }

    public void actualizar() {
        try {
            QueryBuilder.use(Libro.class).whereKey(vista.fieldidLibro.getText()).update(builder -> {
                builder.set("id_Libro", vista.fieldidLibro.getText());
                builder.set("editorial", vista.fieldEditorial.getText());
                builder.set("numero_hojas", vista.fieldNumerohojas.getText());
                builder.set("titulo", vista.fieldTitulo.getText());
                builder.set("autor", vista.fieldAutor.getText());
                builder.set("genero", vista.fieldGenero.getText());
                builder.set("precio", vista.fieldPrecio.getText());
                builder.set("bestseller", esBestSeller());
                builder.set("image_url", vista.fieldImagen.getText());
            });
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vista, e.getMessage(), "SQL error", JOptionPane.ERROR_MESSAGE);
            return;

        }
        cargarTabla();
        limpiar();
    }

    public void borrar() {
        if (vista.fieldidLibro.getText().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Necesitas seleccionar para borrar");
        } else {
            try {
                QueryBuilder.use(Libro.class).whereKey(vista.fieldidLibro.getText()).delete();
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
        vista.fieldBestseller.setActionCommand("SI");
        vista.fieldImagen.setText("");

        vista.table.clearSelection();
    }

    public void seleccionarFila() {
        int fila = vista.table.getSelectedRow();
        if (fila < 0)
            return;

        // Limpiar previsualización anterior
        vista.imagePreview.setIcon(null);
        vista.imagePreview.setText("Cargando...");

        // Los índices deben coincidir con cargarTabla y el modelo de LibroVista
        vista.fieldidLibro.setText(String.valueOf(vista.modeloTabla.getValueAt(fila, 0)));
        vista.fieldEditorial.setText(String.valueOf(vista.modeloTabla.getValueAt(fila, 1)));
        vista.fieldTitulo.setText(String.valueOf(vista.modeloTabla.getValueAt(fila, 2)));
        vista.fieldNumerohojas.setText(String.valueOf(vista.modeloTabla.getValueAt(fila, 3)));
        vista.fieldAutor.setText(String.valueOf(vista.modeloTabla.getValueAt(fila, 4)));
        vista.fieldGenero.setText(String.valueOf(vista.modeloTabla.getValueAt(fila, 5)));
        vista.fieldPrecio.setText(String.valueOf(vista.modeloTabla.getValueAt(fila, 6)));
        vista.fieldBestseller.setActionCommand(String.valueOf(vista.modeloTabla.getValueAt(fila, 7)));

        String imgUrl = String.valueOf(vista.modeloTabla.getValueAt(fila, 8));
        vista.fieldImagen.setText(imgUrl != null && !imgUrl.equals("null") ? imgUrl : "");

        if (imgUrl != null && !imgUrl.isEmpty() && !imgUrl.equals("null")) {
            // Cargar imagen en segundo plano para no congelar la UI
            new Thread(() -> {
                try {
                    ImageIcon originalIcon;
                    if (imgUrl.toLowerCase().startsWith("http")) {
                        originalIcon = new ImageIcon(new URL(imgUrl));
                    } else {
                        originalIcon = new ImageIcon(imgUrl);
                    }

                    if (originalIcon.getIconWidth() > 0) {
                        Image img = originalIcon.getImage();
                        // Escalar para ajustar al panel (150x200 aprox como definimos en Vista)
                        Image scaledImg = img.getScaledInstance(150, 200, Image.SCALE_SMOOTH);
                        ImageIcon scaledIcon = new ImageIcon(scaledImg);

                        SwingUtilities.invokeLater(() -> {
                            vista.imagePreview.setIcon(scaledIcon);
                            vista.imagePreview.setText("");
                        });
                    } else {
                        SwingUtilities.invokeLater(() -> vista.imagePreview.setText("No se pudo cargar"));
                    }
                } catch (Exception ex) {
                    SwingUtilities.invokeLater(() -> vista.imagePreview.setText("URL inválida"));
                }
            }).start();
        } else {
            vista.imagePreview.setText("Sin Imagen");
        }
    }

    public boolean esBestSeller()
    {

        boolean esBestseller = false;

        if( ((String) vista.fieldBestseller.getSelectedItem()).equalsIgnoreCase("Si") )
        {
            esBestseller = true;
        }

        return esBestseller;
    }

    public String decisionBestseller(boolean esBestSeller )
    {

        if( esBestSeller )
        {
            return "SI";
        }

        return "No";
    }

    public boolean verificarFields()
    {
        if ( vista.fieldTitulo.getText().isEmpty() )
        {
            JOptionPane.showMessageDialog(vista, "Necesitas colocar un titulo");
            return false;
        }

        if ( vista.fieldAutor.getText().isEmpty() )
        {
            JOptionPane.showMessageDialog(vista, "Necesitas colocar el author");
            return false;
        }

        return true;
    }
}
