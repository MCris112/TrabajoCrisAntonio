package com.trabajocrisantonio.Controllers;

import com.darkredgm.querymc.Collections.MCList;
import com.darkredgm.querymc.Database.Model;
import com.darkredgm.querymc.Database.ModelAttribute;
import com.darkredgm.querymc.Database.ORM.QueryBuilder;
import com.trabajocrisantonio.Core.PanelField;
import com.trabajocrisantonio.Views.BaseView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

abstract public class BaseController {

    private BaseView view;

    public BaseController(BaseView view) {
        this.view = view;

        initController();
        cargarTabla();
    }

    abstract public Model getModel();

    private void cargarTabla() {

        view.modeloTabla.setRowCount(0);

        try{
            MCList<Model> list = (MCList<Model>) QueryBuilder.use(getModel().getClass()).get();

            for(Model m : list){

                ArrayList<Object> columns = new ArrayList<>();
                for (ModelAttribute attr: m.getFieldAttributes() )
                {
                    columns.add(attr.getValue());
                }

                view.modeloTabla.addRow(columns.toArray());
            }
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(view, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void initController()
    {
        view.btnInsertar.addActionListener( e -> insertar() );
        view.btnActualizar.addActionListener( e -> actualizar() );
        view.btnEliminar.addActionListener( e -> eliminar() );
        view.btnLimpiar.addActionListener( e -> limpiar() );

        view.tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if (!e.getValueIsAdjusting()) {
                            selecionarFila();
                        }
                    }
                });
    }

    public void insertar()
    {
        try{
            Model model = this.getModel().getClass().getConstructor().newInstance();


            for ( ModelAttribute attr: model.getFieldAttributes() )
            {

                PanelField field = view.getFields().get( attr.getName() );

                if ( field != null )
                {
                    field.setValueTo(attr);
                }
            }

            // Lo guarda en la base de datos
            model.save();
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            JOptionPane.showMessageDialog(view, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }

        cargarTabla();
        limpiar();
    }

    private void actualizar() {

        ModelAttribute primaryKey = this.getModel().getFieldPrimaryKey();
        PanelField field = view.getFields().get( primaryKey.getName() );

        if ( field != null )
        {
            try{
                QueryBuilder.use(this.getModel().getClass())
                        .whereKey( field.getValue() )
                        .update( setBuilder -> {

                            for (String key: view.getFields().keySet() )
                            {
                                setBuilder.set(key, view.getFields().get(key).getValue() );
                            }
                        });
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(view, e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        cargarTabla();
        limpiar();
    }

    private void eliminar() {

        ModelAttribute primaryKey = this.getModel().getFieldPrimaryKey();
        PanelField field = view.getFields().get( primaryKey.getName() );

        if ( field != null )
        {
            if ( field.isEmpty() )
            {
                JOptionPane.showMessageDialog(view, "Necesitas seleccionar para eliminar");
            }else {


                try{
                    QueryBuilder.use( this.getModel().getClass() ).whereKey( field.getValueFrom(primaryKey) ).delete();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(view, e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
                }

            }

        }

        cargarTabla();
        limpiar();
    }

    private void limpiar() {
        for (String key: view.getFields().keySet())
        {
            view.getFields().get(key).clear();
        }

        view.tabla.clearSelection();
    }

    public void selecionarFila()
    {
        int fila = view.tabla.getSelectedRow();
        if (fila < 0) return;

        for ( String key: view.getFields().keySet() )
        {
            TableColumn column = view.tabla.getColumn(key);
            PanelField field = view.getFields().get( key );

            if ( column != null &&  field != null )
            {
                int col = column.getModelIndex();
                field.set( view.modeloTabla.getValueAt(fila,
                    col
                    ) );
            }
        }



//        PanelField field = view.getFields().get( col );
//
//        int col = view.modeloTabla.findColumn(column);
//
//        if ( col == -1 ) return;
//
//
    }
}
