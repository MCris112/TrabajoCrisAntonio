package com.trabajocrisantonio.Views;

import com.trabajocrisantonio.Componentes.TabbedPane;
import com.trabajocrisantonio.Controllers.User.PrestarController;
import com.trabajocrisantonio.Views.User.PrestarVista;
import com.trabajocrisantonio.Views.User.UserLibrosVista;
import com.trabajocrisantonio.modelos.Usuario;

import javax.swing.*;

public class UserActivity extends JFrame {

    private Usuario usuario;

    public UserActivity( Usuario usuario) {
        this.usuario = usuario;

        setTitle("Bienvenido "+usuario.getNombre());
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
