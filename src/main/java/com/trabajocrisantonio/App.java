package com.trabajocrisantonio;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {

    public App() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(50,50,300,200);


//        tabbedPane.addTab("Categoria", view);
//        tabbedPane.addTab("Pieza", piezaView);
//        tabbedPane.addTab("Proovedor", proveedorView);
//        tabbedPane.addTab("Suministro", suministroView);

        setContentPane(tabbedPane);
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
