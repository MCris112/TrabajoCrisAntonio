package com.trabajocrisantonio.Componentes;

import javax.swing.*;
import java.awt.*;

public class TabbedPane extends JTabbedPane {

    public TabbedPane(int tabPlacement) {
        super(tabPlacement);

        setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        setFont(new Font("Roboto", Font.BOLD, 14));
        setBackground(new Color(245, 245, 245)); // Gris claro Material
        setForeground(new Color(66, 66, 66));
        setBorder(null);
    }

    @Override
    public void addTab(String title, Component component) {
        super.addTab(title, component);

        int position = getTabCount() -1;

        JLabel lab = new JLabel(title );
        lab.setPreferredSize(new Dimension(160, 45)); // Ancho fijo para la sidebar
        lab.setFont(new Font("Roboto", Font.BOLD, 14));
        lab.setForeground(new Color(33, 150, 243)); // Azul Material
        lab.setHorizontalAlignment(SwingConstants.CENTER);
        setTabComponentAt(position, lab);
    }
}
