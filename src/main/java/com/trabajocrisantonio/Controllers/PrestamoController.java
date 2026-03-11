package com.trabajocrisantonio.Controllers;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.darkredgm.querymc.Collections.MCList;
import com.darkredgm.querymc.Database.ORM.QueryBuilder;
import com.trabajocrisantonio.Views.PrestamoVista;
import com.trabajocrisantonio.modelos.Libro;
import com.trabajocrisantonio.modelos.Prestamo;
import com.trabajocrisantonio.modelos.Usuario;

public class PrestamoController extends javax.swing.JFrame {
    protected PrestamoVista vista;

    public PrestamoController(PrestamoVista vista) {
        this.vista = vista;

        initController();
        cargarTabla();
    }

    private void cargarTabla() {
        vista.modeloTabla.setRowCount(0);

        try {
            MCList<Prestamo> listaPrestamos = QueryBuilder.use(Prestamo.class).get();
            for (Prestamo prestamo : listaPrestamos) {
                // TODO QueryMC problema
                String[] columna = {
                        String.valueOf(prestamo.getId()),
                        String.valueOf(prestamo.getId_libro()),
                        String.valueOf(prestamo.getNif()),
                        prestamo.getFecha_inicio(),
                        prestamo.getFecha_fin(),
                        String.valueOf(prestamo.isDevuelto())
                };

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
            Prestamo prestamo = new Prestamo(
                    Integer.parseInt(vista.fieldidLibro.getText()),
                    Integer.parseInt(vista.fieldid.getText()),
                    vista.fieldNif.getText(),
                    vista.fieldFechaInicio.getText(),
                    vista.fieldFechaFin.getText(),
                    Boolean.parseBoolean(vista.fieldDevuelto.getText())
            );

            prestamo.save();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vista, e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }
        cargarTabla();
        limpiar();
    }

    public void actualizar() {
        try {
            QueryBuilder.use(Usuario.class).whereKey(vista.fieldidLibro.getText()).update(builder -> {
                builder.set("idLibro", vista.fieldidLibro.getText());
                builder.set("Numero prestamo", vista.fieldid.getText());
                builder.set("nif", vista.fieldNif.getText());
                builder.set("Fecha Inicio", vista.fieldFechaInicio.getText());
                builder.set("Fecha Fin", vista.fieldFechaFin.getText());
                builder.set("Devuelto", vista.fieldDevuelto.getText());
            });
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vista, e.getMessage(), "SQL error", JOptionPane.ERROR_MESSAGE);

        }
        cargarTabla();
        limpiar();
    }

    public void borrar() {
        if (vista.fieldid.getText().isEmpty()) {
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
        vista.fieldid.setText("");
        vista.fieldNif.setText("");
        vista.fieldFechaInicio.setText("");
        vista.fieldFechaFin.setText("");
        vista.fieldDevuelto.setText("");


        vista.table.clearSelection();
    }

    public void seleccionarFila() {
        int fila = vista.table.getSelectedRow();
        if (fila < 0) return;

        vista.fieldid.setText((String) vista.modeloTabla.getValueAt(fila, 0));
        vista.fieldidLibro.setText((String) vista.modeloTabla.getValueAt(fila, 1));
        vista.fieldNif.setText((String) vista.modeloTabla.getValueAt(fila, 2));
        vista.fieldFechaInicio.setText((String) vista.modeloTabla.getValueAt(fila, 3));
        vista.fieldFechaFin.setText((String) vista.modeloTabla.getValueAt(fila, 4));
        vista.fieldDevuelto.setText((String) vista.modeloTabla.getValueAt(fila, 5));

    }
}
