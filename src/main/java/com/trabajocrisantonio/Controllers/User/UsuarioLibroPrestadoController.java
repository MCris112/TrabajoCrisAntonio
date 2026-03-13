package com.trabajocrisantonio.Controllers.User;

import com.darkredgm.querymc.Collections.MCList;
import com.darkredgm.querymc.Database.ORM.QueryBuilder;
import com.trabajocrisantonio.Views.User.UserLibrosVista;
import com.trabajocrisantonio.modelos.Prestamo;
import com.trabajocrisantonio.modelos.Usuario;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.sql.SQLException;

public class UsuarioLibroPrestadoController {

    private UserLibrosVista vista;
    private Usuario usuario;

    private Integer prestamoId = null;

    public UsuarioLibroPrestadoController(UserLibrosVista vista, Usuario usuario) {
        this.vista = vista;
        this.usuario = usuario;

        initController();
        cargarTabla();
    }

    public void initController() {
        vista.btnDevolver.addActionListener( e -> {
            if ( prestamoId == null ) {
                JOptionPane.showMessageDialog(null, "Seleccione un prestamo para devolver");
                return;
            }

            try{
                QueryBuilder.use(Prestamo.class).whereKey( prestamoId ).update( setBuilder -> {
                    setBuilder.set("devuelto", true);
                });
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(vista, ex.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
            }

            cargarTabla();
        });

        vista.tableLibros.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    seleccionarFila();
                }
            }
        });
    }

    public void cargarTabla()
    {
        vista.modeloTabla.setRowCount(0);

        try{
            MCList<Prestamo> listaLibros = QueryBuilder.use(Prestamo.class)
                    .where("usuario_nif", this.usuario.getNif())
                    .where("devuelto", false)
                    .get();

            for (Prestamo prestamo : listaLibros) {
                String[] columna = {String.valueOf(prestamo.getId()), String.valueOf(prestamo.getId_libro()), prestamo.getFecha_inicio(), prestamo.getFecha_fin(), String.valueOf(prestamo.isDevuelto())};

                vista.modeloTabla.addRow(
                        columna
                );
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vista, e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void seleccionarFila()
    {
        int fila = vista.tableLibros.getSelectedRow();
        if (fila < 0)
            return;

        this.prestamoId = Integer.parseInt((String) vista.modeloTabla.getValueAt(fila, 0));
    }
}
