package com.trabajocrisantonio.Controllers;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.darkredgm.querymc.Collections.MCList;
import com.darkredgm.querymc.Database.ORM.QueryBuilder;
import com.trabajocrisantonio.Views.Admin.PrestamoVista;
import com.trabajocrisantonio.modelos.Prestamo;

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

            System.out.println("----------");

            for (Prestamo prestamo : listaPrestamos) {

                System.out.println( prestamo );

                // TODO QueryMC problema
                String[] columna = {
                        String.valueOf(prestamo.getId()),
                        String.valueOf(prestamo.getId_libro()),
                        String.valueOf(prestamo.getNif()),
                        prestamo.getFecha_inicio(),
                        prestamo.getFecha_fin(),
                        decisionDevuelto(prestamo.isDevuelto()) // Si | No
                };

                vista.modeloTabla.addRow(
                        columna
                );
            }

            System.out.println("----------");

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

        if ( !validarFields() )
            return;

        Integer id = null;

        if ( !vista.fieldid.getText().isEmpty() )
        {
            id =  Integer.parseInt(vista.fieldid.getText());
        }



        try {
            Prestamo prestamo = new Prestamo(
                    id,
                    Integer.parseInt(vista.fieldidLibro.getText()),
                    vista.fieldNif.getText(),
                    vista.fieldFechaInicio.getText(),
                    vista.fieldFechaFin.getText(),
                    esDevuelto()
            );

            prestamo.save();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vista, e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }
        cargarTabla();
        limpiar();
    }

    public void actualizar() {


        if ( !validarFields() )
            return;

        try {

            QueryBuilder.use(Prestamo.class).whereKey(vista.fieldidLibro.getText()).update(builder -> {

                // Tira error
               // builder.set("id", vista.fieldid.getText());
                builder.set("id_libro", vista.fieldidLibro.getText());
                builder.set("nif", vista.fieldNif.getText());
                builder.set("fecha_inicio", vista.fieldFechaInicio.getText());
                builder.set("fecha_fin", vista.fieldFechaFin.getText());
                builder.set("devuelto", esDevuelto() );
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
                QueryBuilder.use(Prestamo.class).whereKey(vista.fieldid.getText()).delete();
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
        vista.fieldDevuelto.setActionCommand("Si");


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

        vista.fieldDevuelto.setActionCommand((String) vista.modeloTabla.getValueAt(fila, 5));

    }

    public boolean esDevuelto()
    {

        boolean esDevuelto = false;

        if( ((String) vista.fieldDevuelto.getSelectedItem()).equalsIgnoreCase("Si") )
        {
            esDevuelto = true;
        }

        return esDevuelto;
    }

    /**
     * En base al boolean de la tabla, convierte el valor en formato string
     * @return Si | No
     */
    public String decisionDevuelto( boolean esDevuelto )
    {

        if( esDevuelto )
        {
            return "SI";
        }

        return "No";
    }

    public boolean validarFields()
    {

        if ( vista.fieldidLibro.getText().isEmpty() ) {
            JOptionPane.showMessageDialog(null, "Debe ingresar el libro", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if ( vista.fieldNif.getText().isEmpty() )
        {
            JOptionPane.showMessageDialog(null, "Debe ingresar el nif", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Comparar si fecha fin no puede ser menor que fecha inicio
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false); // Validación estricta

            Date fechaInicio = sdf.parse(vista.fieldFechaInicio.getText());
            Date fechaFin = sdf.parse(vista.fieldFechaFin.getText());

            if (fechaFin.before(fechaInicio)) {
                JOptionPane.showMessageDialog(null, "La fecha de fin no puede ser anterior a la de inicio", "Error de Fecha", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Asegúrese de rellenar las fechas correctamente (dd/mm/aaaa)", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
}
