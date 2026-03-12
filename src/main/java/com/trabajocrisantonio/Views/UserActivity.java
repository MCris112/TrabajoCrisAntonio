package com.trabajocrisantonio.Views;

import com.trabajocrisantonio.Componentes.TabbedPane;
import com.trabajocrisantonio.Controllers.User.UserSolicitarLibroController;
import com.trabajocrisantonio.Controllers.User.UsuarioLibroPrestadoController;
import com.trabajocrisantonio.Views.User.UserSolicitarLibroVista;
import com.trabajocrisantonio.Views.User.UserLibrosVista;
import com.trabajocrisantonio.modelos.Usuario;

import javax.swing.*;

public class UserActivity extends JFrame {

    private Usuario usuario;
    private UsuarioLibroPrestadoController prestadoController;

    public UserActivity( Usuario usuario) {
        this.usuario = usuario;

        setTitle("Bienvenido "+usuario.getNombre());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TabbedPane tabbedPane = new TabbedPane( JTabbedPane.LEFT );

        // Inicializar Vistas
        UserLibrosVista userLibrosVista = new UserLibrosVista();
        UserSolicitarLibroVista userSolicitarLibroVista = new UserSolicitarLibroVista();

        // Controladores
        prestadoController = new UsuarioLibroPrestadoController(userLibrosVista, usuario);
        new UserSolicitarLibroController(userSolicitarLibroVista, usuario);

        // Añadir tabs
        tabbedPane.addTab("Mis libros prestados", userLibrosVista);
        tabbedPane.addTab("Solicitar", userSolicitarLibroVista);

        // Refrescar tabla cuando se cambia de pestaña
        tabbedPane.addChangeListener(e -> {
            if (tabbedPane.getSelectedIndex() == 0) { // Tab de "Mis libros prestados"
                prestadoController.cargarTabla();
            }
        });

        // Añadir a la ventana
        add(tabbedPane);

        // Configuración final de la ventana
        setSize(800, 500);
        setLocationRelativeTo(null); // Centrar después de dar tamaño
        setVisible(true); // Mostrar al final
    }

}
