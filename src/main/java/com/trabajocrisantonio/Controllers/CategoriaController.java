package com.trabajocrisantonio.Controllers;

import Models.Categoria;
import Views.BaseView;
import com.darkredgm.querymc.Database.Model;

public class CategoriaController extends BaseController{

    public CategoriaController(BaseView view) {
        super(view);
    }

    @Override
    public Model getModel() {
        return new Categoria();
    }

//    private CategoriaView view;
//
//    public CategoriaController(CategoriaView view){
//        this.view = view;
//
//        initController();
//        cargarTabla();
//    }
//
//
//    private void initController() {
//        view.btnInsertar.addActionListener( e -> insertar() );
//        view.btnActualizar.addActionListener( e -> actualizar() );
//        view.btnEliminar.addActionListener( e -> eliminar() );
//        view.btnLimpiar.addActionListener( e -> limpiar() );
//
//        view.tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//                    @Override
//                    public void valueChanged(ListSelectionEvent e) {
//                        if (!e.getValueIsAdjusting()) {
//                            selecionarFila();
//                        }
//                    }
//                });
//    }
//
//    public void insertar() {
//        try{
//            Categoria categoria = new Categoria(
//                    Integer.parseInt( view.txtCodigo.getText() ),
//                    view.txtNombre.getText()
//            );
//
//            categoria.save();
//
//            cargarTabla();
//            limpiar();
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog( view, e.getMessage(),  "Error", JOptionPane.ERROR_MESSAGE );
//        }
//    }
//
//
//    private void actualizar() {
//
//        int fila = view.tabla.getSelectedRow();
//        if (fila <= 0)
//            return;
//
//        try{
//            int codigo = Integer.parseInt(view.txtCodigo.getText());
//            String nombre = view.txtNombre.getText();
//
//            QueryBuilder.use(Categoria.class).whereKey(codigo).update( builder ->
//                    builder.set("nombre", nombre) );
//
//            cargarTabla();
//            limpiar();
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog( view, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE );
//        }
//
//    }
//
//    private void eliminar() {
//        int fila = view.tabla.getSelectedRow();
//        if (fila <= 0)
//            return;
//
//        try{
//            int codigo = Integer.parseInt(
//                    view.modeloTabla.getValueAt(fila, 0).toString()
//            );
//
//            QueryBuilder.use(Categoria.class).whereKey(codigo).delete();
//
//            cargarTabla();
//            limpiar();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    private void selecionarFila() {
//        int fila = view.tabla.getSelectedRow();
//        if (fila <= 0) return;
//
//        view.txtCodigo.setText( view.modeloTabla.getValueAt(fila, 0).toString() );
//        view.txtNombre.setText( view.modeloTabla.getValueAt(fila, 1).toString() );
//    }
//
//
//    private void limpiar() {
//        view.txtCodigo.setText("");
//        view.txtNombre.setText("");
//        view.tabla.clearSelection();
//    }
//
//    private void cargarTabla() {
//
//        view.modeloTabla.setRowCount(0);
//
//        try{
//
//            MCList<Categoria> lista = QueryBuilder.use(Categoria.class).get();
//
//            for (Categoria categoria : lista) {
//                view.modeloTabla.addRow(new Object[]{
//                        categoria.getId(),
//                        categoria.getNombre()
//                });
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

}
