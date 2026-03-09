package com.trabajocrisantonio.Core;

import com.darkredgm.querymc.Database.ModelAttribute;

import javax.swing.*;

public class PanelField {

    private JTextField field;
    private JComboBox jSelector;


//    public static PanelField from(ModelAttribute attribute)
//    {
//
//
//        return field;
//    }

    public PanelField(JTextField field) {
        this.field = field;
    }

    public JTextField getField() {
        return field;
    }

    public void setValueTo(ModelAttribute attribute) throws IllegalAccessException {
        String value = field.getText();
        if (value == null || value.equals("")) {
            return;
        }

        Class<?> type = attribute.asField().getType();
        if (type != Integer.class && type != Integer.TYPE) {
            if (type != Double.class && type != Double.TYPE) {
                attribute.setValue( value );
            } else {
                attribute.setValue( Double.parseDouble(value) );
            }
        } else {
            attribute.setValue( Integer.parseInt(value) );
        }
    }

    public void set( Object value)
    {
        field.setText( value.toString() );
    }

    public void clear()
    {
        field.setText("");
    }

    public boolean isEmpty()
    {
        return field.getText().isEmpty();
    }

    public String  getValue()
    {
        return field.getText();
    }

    public Object getValueFrom(ModelAttribute attribute)
    {
        Class<?> type = attribute.asField().getType();
        if (type != Integer.class && type != Integer.TYPE) {
            if (type != Double.class && type != Double.TYPE) {
                return getValue();
            } else {
                return Double.parseDouble(getValue());
            }
        } else {
            return Integer.parseInt(getValue() );
        }
    }
}
