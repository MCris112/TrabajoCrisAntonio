package com.trabajocrisantonio.Views;

import com.trabajocrisantonio.Componentes.TabbedPane;
import com.trabajocrisantonio.Controllers.User.PrestarController;
import com.trabajocrisantonio.Views.User.PrestarVista;
import com.trabajocrisantonio.Views.User.UserLibrosVista;

import javax.swing.*;

public class UserActivity extends JFrame {

    private String userNif;

    public UserActivity( String userNif) {
        this.userNif = userNif;

        setTitle("User Activity");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TabbedPane tabbedPane = new TabbedPane( JTabbedPane.LEFT );

        // Inicializar Vistas
        UserLibrosVista userLibrosVista = new UserLibrosVista();
        PrestarVista prestarVista = new PrestarVista();

        // Controladores
        new PrestarController(prestarVista);

        // Añadir tabs
        tabbedPane.addTab("Mis libros prestados", userLibrosVista);
        tabbedPane.addTab("Solicitar", prestarVista);

        // Añadir a la ventana
        add(tabbedPane);

        // Configuración final de la ventana
        setSize(800, 500);
        setLocationRelativeTo(null); // Centrar después de dar tamaño
        setVisible(true); // Mostrar al final
    }

}
