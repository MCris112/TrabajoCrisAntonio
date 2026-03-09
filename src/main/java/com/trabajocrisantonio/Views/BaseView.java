package com.trabajocrisantonio.Views;

import Core.PanelField;
import com.darkredgm.querymc.Annotations.DBColPrimary;
import com.darkredgm.querymc.Database.Model;
import com.darkredgm.querymc.Database.ModelAttribute;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

abstract public class BaseView extends JPanel {

    Map<String, PanelField> fields = new HashMap();

    public JTextField txtCodigo = new JTextField(5);
    public JTextField txtNombre = new JTextField(15);

    public JButton btnInsertar = new JButton("Insertar");
    public JButton btnEliminar = new JButton("Eliminar");
    public JButton btnActualizar = new JButton("Actualizar");
    public JButton btnLimpiar  = new JButton("Limpiar");

    //Tabla
    public DefaultTableModel modeloTabla;

    public JTable tabla;

    JPanel panelFormulario;

    public BaseView() {
        modeloTabla = new DefaultTableModel(this.getTableHeaders(), 0);
        tabla = new JTable(modeloTabla);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS) );

        // ===== Panel Superior ===


         panelFormulario = new JPanel();
        panelFormulario.setBorder(
                BorderFactory.createTitledBorder("Datos categoria")
        );

        generateFields();

        JPanel panelBtn = new JPanel();
        panelBtn.add(btnInsertar);
        panelBtn.add(btnActualizar);
        panelBtn.add(btnEliminar);
        panelBtn.add(btnLimpiar);

        // ==== Panel Interior =======
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(
                BorderFactory.createTitledBorder("Listadp categorias")
        );

        add( panelBtn );
        add( panelFormulario );
        add(scroll );
    }

    public Map<String, PanelField> getFields() {
        return fields;
    }

    abstract public Model getModel();

    private Object[] getTableHeaders()
    {
        ArrayList<Object> headers = new ArrayList<>();

        for (ModelAttribute attr: getModel().getFieldAttributes() )
        {
            headers.add(attr.getName());
        }

        return  headers.toArray();
    }

    private void generateFields() {

        for (ModelAttribute attr: getModel().getFieldAttributes() )
        {
            PanelField field = new PanelField( new JTextField(6) );

            if ( attr.isPrimaryKey() )
            {
                DBColPrimary primary = attr.asField().getAnnotation(DBColPrimary.class);

                // En caso la clave primaria sea dinamica, desactivar el código para editar
                if ( primary.autoincrement() )
                {
                    field.getField().setEditable(false);
                }
            }
            this.fields.put(attr.getName(), field);

            panelFormulario.add(new JLabel(attr.getName()));
            panelFormulario.add( field.getField() );
        }
    }


}
