package com.trabajocrisantonio.Controllers.User;

import com.darkredgm.querymc.Collections.MCList;
import com.darkredgm.querymc.Database.ORM.QueryBuilder;
import com.trabajocrisantonio.Views.User.UserLibrosVista;
import com.trabajocrisantonio.modelos.Prestamo;
import com.trabajocrisantonio.modelos.Usuario;

import javax.swing.*;
import java.sql.SQLException;

public class UsuarioLibroPrestadoController {

    private UserLibrosVista vista;
    private Usuario usuario;

    public UsuarioLibroPrestadoController(UserLibrosVista vista, Usuario usuario) {
        this.vista = vista;
        this.usuario = usuario;

        cargarTabla();
    }

    public void cargarTabla()
    {
        vista.modeloTabla.setRowCount(0);

        try{
            MCList<Prestamo> listaLibros = QueryBuilder.use(Prestamo.class).where("nif", this.usuario.getNif()).get();

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
}
