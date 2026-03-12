package com.trabajocrisantonio.Componentes;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MatComboBox<E> extends JComboBox<E> {

    public MatComboBox() {
        super();
        initStyles();
    }

    public MatComboBox(E[] items) {
        super(items);
        initStyles();
    }

    private void initStyles() {
        setPreferredSize(new Dimension(200, 35));
        setFont(new Font("Roboto", Font.PLAIN, 14));
        setBackground(Color.WHITE);
        
        // Borde similar al MatTextField
        Border lineBorder = BorderFactory.createLineBorder(new Color(220, 220, 220));
        Border padding = BorderFactory.createEmptyBorder(2, 5, 2, 5);
        setBorder(BorderFactory.createCompoundBorder(lineBorder, padding));

        // Renderizado de los elementos (padding interno)
        setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                if (isSelected) {
                    label.setBackground(new Color(33, 150, 243)); // Azul Material
                    label.setForeground(Color.WHITE);
                } else {
                    label.setBackground(Color.WHITE);
                    label.setForeground(new Color(66, 66, 66));
                }
                return label;
            }
        });

        // Hacerlo menos cuadrado y quitar el efecto 3D si es posible (depende del L&F)
        setFocusable(false); // Evita el borde azul feo de foco en algunos sistemas
    }
}
