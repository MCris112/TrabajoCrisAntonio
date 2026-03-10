package com.trabajocrisantonio.Views;

import com.trabajocrisantonio.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Pantalla de selección de rol (Usuario o Administrador).
 * Diseño minimalista siguiendo Material Design.
 */
public class SeleccionarVista extends JFrame {

    public SeleccionarVista() {
        setTitle("Seleccionar Perfil");
        setUndecorated(true); // Estilo limpio sin bordes
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel Principal con degradado sutil o color sólido Material
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230), 2));

        // --- Header (Título) ---
        JLabel lblTitulo = new JLabel("¿Cómo desea ingresar?", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Roboto", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(33, 33, 33));
        lblTitulo.setPreferredSize(new Dimension(500, 80));
        mainPanel.add(lblTitulo, BorderLayout.NORTH);

        // --- Panel de Botones (Centro) ---
        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 20, 0));
        panelBotones.setBackground(Color.WHITE);
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 40, 40, 40));

        JButton btnUsuario = createRoleButton("MODO USUARIO", new Color(33, 150, 243));
        JButton btnAdmin = createRoleButton("MODO ADMIN", new Color(66, 66, 66));

        // Acciones
        btnUsuario.addActionListener(e -> {
            new UserActivity();
            this.dispose();
        });

        btnAdmin.addActionListener(e -> {
            new App();
            this.dispose();
        });

        panelBotones.add(btnUsuario);
        panelBotones.add(btnAdmin);

        mainPanel.add(panelBotones, BorderLayout.CENTER);

        // Añadimos los componentes a la vista
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.add(lblTitulo, BorderLayout.CENTER);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        setContentPane(mainPanel);
        setVisible(true);
    }

    /**
     * Crea un botón estilizado como una tarjeta Material Design.
     */
    private JButton createRoleButton(String text, Color baseColor) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Roboto", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(baseColor);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(baseColor.darker(), 1),
                BorderFactory.createEmptyBorder(20, 10, 20, 10)));

        // Efecto Hover simple
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(baseColor.brighter());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(baseColor);
            }
        });

        return btn;
    }
}
