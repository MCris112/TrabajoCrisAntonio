package com.trabajocrisantonio.Views;

import com.trabajocrisantonio.Controllers.User.PrestarController;
import com.trabajocrisantonio.Views.User.PrestarVista;
import com.trabajocrisantonio.Views.User.UserLibrosVista;

import javax.swing.*;

public class UserActivity extends JFrame {
    public UserActivity() {
        setTitle("User Activity");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

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

    public static void main(String[] args) {
        new UserActivity();
    }
}
