package com.trabajocrisantonio.Componentes;

import javax.swing.*;
import java.awt.*;

public class MatTextField extends JTextField {

    public MatTextField() {
        initStyles();
    }

    public MatTextField(int columns) {
        super(columns);
        initStyles();
    }

    public void initStyles()
    {
        setPreferredSize(new Dimension(200, 35));
        setFont(new Font("Roboto", Font.PLAIN, 14));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        setBackground(Color.WHITE);
    }
}
