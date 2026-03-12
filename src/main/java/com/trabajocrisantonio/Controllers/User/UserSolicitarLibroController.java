package com.trabajocrisantonio.Controllers.User;

import com.darkredgm.querymc.Collections.MCList;
import com.darkredgm.querymc.Database.ORM.QueryBuilder;
import com.trabajocrisantonio.Views.User.UserSolicitarLibroVista;
import com.trabajocrisantonio.modelos.Libro;
import com.trabajocrisantonio.modelos.Prestamo;
import com.trabajocrisantonio.modelos.Usuario;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Controlador para la vista de solicitar libros por parte del usuario.
 * Maneja la búsqueda y la creación automática de préstamos.
 */
public class UserSolicitarLibroController {

    private UserSolicitarLibroVista vista;
    private Usuario usuarioActual;

    public UserSolicitarLibroController(UserSolicitarLibroVista vista, Usuario usuario) {
        this.vista = vista;
        this.usuarioActual = usuario;
        initController();
    }

    private void initController() {
        // Acción de buscar
        vista.btnBuscar.addActionListener(e -> buscarLibros());
        
        // También buscar al presionar Enter en el campo
        vista.fieldBuscar.addActionListener(e -> buscarLibros());

        // Acción de solicitar (prestar)
        vista.btnPrestar.addActionListener(e -> solicitarLibro());

        // Cargar todos los libros al iniciar
        buscarLibros();
    }

    /**
     * Busca libros por título o autor y llena la tabla.
     */
    private void buscarLibros() {
        String query = vista.fieldBuscar.getText().trim();
        vista.modeloTabla.setRowCount(0);

        try {
            MCList<Libro> libros;
            if (query.isEmpty()) {
                libros = QueryBuilder.use(Libro.class).get();
            } else {
                libros = QueryBuilder.use(Libro.class)
                        .where("titulo", "LIKE", "%" + query + "%")
                        .orWhere("autor", "LIKE", "%" + query + "%")
                        .get();
            }

            for (Libro libro : libros) {
                vista.modeloTabla.addRow(new Object[]{
                        libro.getId_libro(),
                        libro.getTitulo(),
                        libro.getAutor(),
                        "Disponible"
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vista, "Error al buscar libros: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Crea un nuevo préstamo para el libro seleccionado.
     * Fecha inicio: Hoy.
     * Fecha fin: 30 días después.
     */
    private void solicitarLibro() {
        int fila = vista.tableResultados.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(vista, "Por favor, seleccione un libro de la tabla para solicitarlo.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Obtener ID del libro de la primera columna
        int idLibro = (int) vista.modeloTabla.getValueAt(fila, 0);
        String titulo = (String) vista.modeloTabla.getValueAt(fila, 1);

        // Lógica de fechas (Hoy + 30 días)
        LocalDate hoy = LocalDate.now();
        LocalDate fechaDevolucion = hoy.plusDays(30);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String strHoy = hoy.format(dtf);
        String strFin = fechaDevolucion.format(dtf);

        try {
            // Crear el objeto préstamo
            Prestamo prestamo = new Prestamo(
                    null, // ID autoincremental
                    idLibro,
                    usuarioActual.getNif(),
                    strHoy,
                    strFin,
                    false // Aun no devuelto
            );

            // Guardar en Base de Datos
            prestamo.save();

            JOptionPane.showMessageDialog(vista, 
                "¡Solicitud exitosa!\n" +
                "Libro: " + titulo + "\n" +
                "Fecha de préstamo: " + strHoy + "\n" +
                "Fecha límite de devolución: " + strFin,
                "Préstamo Realizado", 
                JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vista, "No se pudo procesar la solicitud: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
        }
    }
}
