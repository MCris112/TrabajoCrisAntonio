package com.trabajocrisantonio.Controllers;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.darkredgm.querymc.Collections.MCList;
import com.darkredgm.querymc.Database.ORM.QueryBuilder;
import com.trabajocrisantonio.Views.UsuarioVista;
import com.trabajocrisantonio.modelos.Usuario;

public class UsuarioControlador {
    private UsuarioVista vista;
    public UsuarioControlador(UsuarioVista vista){
        this.vista = vista;

        initController();
        cargarTabla();
    }

    private void cargarTabla(){
        vista.modeloTabla.setRowCount(0);

        try{
            MCList<Usuario> listaUsuarios = QueryBuilder.use(Usuario.class).get();
         for(Usuario usuario: listaUsuarios){
        String[] columna = {usuario.getNif(),usuario.getNombre(),usuario.getApellido1(),usuario.getDireccion(),usuario.getTelefono()};

                vista.modeloTabla.addRow(
                    columna
                );
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(vista, e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public void initController(){

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
    public void insertar(){
        try{
            Usuario usuario = new Usuario(
                vista.fieldNif.getText(),
                vista.fieldNombre.getText(),
                vista.fieldApellido.getText(), 
                vista.fieldDireccion.getText(), 
                vista.fieldTelefono.getText()
            );

            usuario.save();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(vista, e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }
        cargarTabla();
        limpiar();
    }
    public void actualizar(){}
    public void borrar(){}
    public void limpiar(){
        vista.fieldNif.setText("");
        vista.fieldNombre.setText("");
        vista.fieldApellido.setText("");
        vista.fieldDireccion.setText("");
        vista.fieldTelefono.setText("");
    }
    public void seleccionarFila(){}

    
    
}
