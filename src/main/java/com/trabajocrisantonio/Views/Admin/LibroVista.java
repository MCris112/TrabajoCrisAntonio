package com.trabajocrisantonio.Views.Admin;

import com.trabajocrisantonio.Componentes.MatComboBox;
import com.trabajocrisantonio.Componentes.MatTextField;
import com.trabajocrisantonio.SoloNumerosAdapter;
import com.trabajocrisantonio.Style;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LibroVista extends JPanel {
    public JButton btnInsertar = new JButton("Insertar");
    public JButton btnActualizar = new JButton("Actualizar");
    public JButton btnBorrar = new JButton("Borrar");
    public JButton btnLimpiar = new JButton("Limpiar");

    public MatTextField fieldidLibro = new MatTextField();
    public MatTextField fieldEditorial = new MatTextField();
    public MatTextField fieldNumerohojas = new MatTextField();
    public MatTextField fieldTitulo = new MatTextField();
    public MatTextField fieldAutor = new MatTextField();
    public MatTextField fieldGenero = new MatTextField();
    public MatTextField fieldPrecio = new MatTextField();
    public MatComboBox<String> fieldBestseller = new MatComboBox<>(new String[] { "SI", "NO" });
    public MatTextField fieldImagen = new MatTextField(); // URL de la imagen

    public JLabel imagePreview = new JLabel("Sin Imagen"); // Previsualización

    public DefaultTableModel modeloTabla = new DefaultTableModel(
            new Object[]{
                    "idLibro",
                    "editorial",
                    "titulo",
                    "numeroHojas",
                    "autor",
                    "género",
                    "precio",
                    "bestseller",
                    "imagen"
            },
            0);
    public JTable table = new JTable(modeloTabla);

    public LibroVista() {
        setLayout(new BorderLayout(20, 20));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 1. Panel Superior (Botones y Formulario)
        JPanel topContainer = new JPanel(new BorderLayout(10, 20));
        topContainer.setBackground(Color.WHITE);

        // --- Botones (Crud) ---
        JPanel panelBtn = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelBtn.setBackground(Color.WHITE);

        Style.button(btnInsertar, new Color(33, 150, 243));
        Style.button(btnBorrar, new Color(244, 67, 54));
        Style.button(btnActualizar, new Color(76, 175, 80));
        Style.button(btnLimpiar, new Color(158, 158, 158));

        panelBtn.add(btnInsertar);
        panelBtn.add(btnBorrar);
        panelBtn.add(btnActualizar);
        panelBtn.add(btnLimpiar);

        // --- Formulario (GridBagLayout) ---
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(Color.WHITE);
        panelFormulario.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230)), "DATOS DEL LIBRO"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 12, 8, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 1: ID y Título
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        panelFormulario.add(new JLabel("ID Libro"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        panelFormulario.add(fieldidLibro, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0;
        panelFormulario.add(new JLabel("Título"), gbc);
        gbc.gridx = 3;
        gbc.weightx = 1.0;
        panelFormulario.add(fieldTitulo, gbc);

        // Fila 2: Autor y Género
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        panelFormulario.add(new JLabel("Autor"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        panelFormulario.add(fieldAutor, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0;
        panelFormulario.add(new JLabel("Género"), gbc);
        gbc.gridx = 3;
        gbc.weightx = 1.0;
        panelFormulario.add(fieldGenero, gbc);

        // Fila 3: Editorial y Hojas
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        panelFormulario.add(new JLabel("Editorial"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        panelFormulario.add(fieldEditorial, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0;
        panelFormulario.add(new JLabel("Nº Hojas"), gbc);
        gbc.gridx = 3;
        gbc.weightx = 1.0;
        panelFormulario.add(fieldNumerohojas, gbc);

        // Fila 4: Precio y Bestseller
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        panelFormulario.add(new JLabel("Precio"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        panelFormulario.add(fieldPrecio, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0;
        panelFormulario.add(new JLabel("Bestseller"), gbc);
        gbc.gridx = 3;
        gbc.weightx = 1.0;
        panelFormulario.add(fieldBestseller, gbc);

        // Fila 5: URL Imagen
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0;
        panelFormulario.add(new JLabel("URL Imagen"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        panelFormulario.add(fieldImagen, gbc);

        // --- Panel Previsualización de Imagen ---
        JPanel panelImagen = new JPanel(new BorderLayout());
        panelImagen.setBackground(Color.WHITE);
        panelImagen.setPreferredSize(new Dimension(150, 200));
        panelImagen.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230)), "PORTADA"));

        imagePreview.setHorizontalAlignment(SwingConstants.CENTER);
        panelImagen.add(imagePreview, BorderLayout.CENTER);

        // Unir formulario e imagen en un panel horizontal
        JPanel headerForm = new JPanel(new BorderLayout(20, 0));
        headerForm.setBackground(Color.WHITE);
        headerForm.add(panelFormulario, BorderLayout.CENTER);
        headerForm.add(panelImagen, BorderLayout.EAST);

        topContainer.add(panelBtn, BorderLayout.NORTH);
        topContainer.add(headerForm, BorderLayout.CENTER);

        // 2. Tabla (Centro)
        Style.tabla(table);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230)), "LISTADO DE LIBROS"));
        scroll.getViewport().setBackground(Color.WHITE);

        add(topContainer, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);



        // --- Restricciones Numéricas ---

        // Restricción para Id del libro
        fieldidLibro.addKeyListener( new SoloNumerosAdapter() );

        // Restricción para Número de Hojas
        fieldNumerohojas.addKeyListener( new SoloNumerosAdapter() );

        // Restricción para Precio
        fieldPrecio.addKeyListener( new SoloNumerosAdapter() );
    }
}
