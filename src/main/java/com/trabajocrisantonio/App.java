package com.trabajocrisantonio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.trabajocrisantonio.Controllers.LibroController;
import com.trabajocrisantonio.Controllers.UsuarioControlador;
import com.trabajocrisantonio.Views.LibroVista;
import com.trabajocrisantonio.Views.UsuarioVista;

public class App extends JFrame {

    // Variables para el arrastre de la ventana (necesario si es undecorated)
    private int xMouse, yMouse;

    private JPanel toolbar;

    public App() {
        setUndecorated(true); // Estilo Material real sin marcos del sistema
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Contenedor principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Toolbar Material
        initToolbar();

        // App Body (Tabs en la izquierda)
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane.setFont(new Font("Roboto", Font.BOLD, 14));
        tabbedPane.setBackground(new Color(245, 245, 245)); // Gris claro Material
        tabbedPane.setForeground(new Color(66, 66, 66));
        tabbedPane.setBorder(null); // Quitar bordes para que parezca una sidebar integrada

        UsuarioVista usuarioVista = new UsuarioVista();
        tabbedPane.addTab("Usuarios", usuarioVista);

        // Estilo de "caja" para la pestaña
        JLabel lab = new JLabel("Usuarios");
        lab.setPreferredSize(new Dimension(150, 40)); // Ancho fijo para la sidebar
        lab.setFont(new Font("Roboto", Font.BOLD, 14));
        lab.setForeground(new Color(33, 150, 243)); // Azul Material
        lab.setHorizontalAlignment(SwingConstants.CENTER);
        tabbedPane.setTabComponentAt(0, lab);

        // Inicializar controlador (si existe)
        try {
            new UsuarioControlador(usuarioVista);
        } catch (Exception e) {
            System.err.println("No se pudo cargar el controlador: " + e.getMessage());
        }

        LibroVista libroVista = new LibroVista();
        new LibroController( libroVista);
        tabbedPane.add("Libros", libroVista);

        // Unir todo
        mainPanel.add(toolbar, BorderLayout.NORTH);
        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        setContentPane(mainPanel);
        setVisible(true);
    }

    public void initToolbar()
    {
        toolbar = new JPanel(new BorderLayout());
        toolbar.setPreferredSize(new Dimension(0, 50));
        toolbar.setBackground(Color.WHITE);

        // Soporte para arrastrar la ventana desde el toolbar
        toolbar.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                xMouse = e.getX();
                yMouse = e.getY();
            }
        });
        toolbar.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                setLocation(x - xMouse, y - yMouse);
            }
        });

        // Botón cerrar (Simulado con un Panel y Label)
        JPanel closeBtnContainer = new JPanel(new GridBagLayout());
        closeBtnContainer.setPreferredSize(new Dimension(50, 50));
        closeBtnContainer.setBackground(Color.WHITE);
        closeBtnContainer.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel closeLabel = new JLabel("x"); // Símbolo X
        closeLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
        closeLabel.setForeground(Color.BLACK);
        closeBtnContainer.add(closeLabel);

        // Eventos Hover para el botón cerrar
        closeBtnContainer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                closeBtnContainer.setBackground(Color.RED);
                closeLabel.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                closeBtnContainer.setBackground(Color.WHITE);
                closeLabel.setForeground(Color.BLACK);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        toolbar.add(closeBtnContainer, BorderLayout.EAST);

        // Titulo en Toolbar
        JLabel title = new JLabel("     Gestor Biblioteca - Trabajo Cristopher - Antonio");
        title.setFont(new Font("Roboto", Font.BOLD, 14));
        title.setForeground(new Color(33, 33, 33));
        toolbar.add(title, BorderLayout.WEST);
    }
}
