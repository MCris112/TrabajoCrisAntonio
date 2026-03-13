package com.trabajocrisantonio;

import java.sql.SQLException;
import java.util.List;

import com.darkredgm.querymc.Database.DB;
import com.darkredgm.querymc.Database.ModelAttribute;
import com.trabajocrisantonio.modelos.Libro;
import com.trabajocrisantonio.modelos.Prestamo;
import com.trabajocrisantonio.modelos.Usuario;

public class Migracion {
    public static void main(String[] args) throws SQLException {

        try {
            DB.deleteDatabase("tareaDomingo");
        } catch (SQLException e) {
        }

        DB.createDatabase("tareaDomingo");
        DB.verify(Libro.class, Usuario.class, Prestamo.class);

        // Cargar datos iniciales
        try {
            cargarValoresPorDefecto();
            System.out.println("✅ Datos por defecto cargados correctamente.");
        } catch (SQLException e) {
            System.err.println("❌ Error cargando datos: " + e.getMessage());
        }
    }

    /**
     * Genera datos de prueba para la base de datos.
     */
    public static void cargarValoresPorDefecto() throws SQLException {
        // --- 1. Usuarios ---
        Usuario u1 = new Usuario("12345678A", "Cristopher", "Rodriguez", "Calle Mayor 1", "600111222");
        u1.save();
        Usuario u2 = new Usuario("87654321B", "Antonio", "García", "Avenida Libertad 5", "600333444");
        u2.save();
        Usuario u3 = new Usuario("11223344C", "Admin", "General", "Biblioteca Central", "900000000");
        u3.save();

        // --- 2. Libros (con URLs de portadas reales) ---
        Libro l1 = new Libro(null, "Planeta", 400, "Don Quijote de la Mancha", "Miguel de Cervantes", "Aventura", 25, true,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/0/08/Don_Quijote_1.jpg/220px-Don_Quijote_1.jpg");
        l1.save();


        System.out.println(l1);

        Libro l2 = new Libro(null, "Salamandra", 300, "Harry Potter y la Piedra Filosofal", "J.K. Rowling", "Fantasía", 22, true,
                "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1546910265i/3.jpg");
        l2.save();

        Libro l3 = new Libro(null, "Debolsillo", 1200, "El Señor de los Anillos", "J.R.R. Tolkien", "Fantasía", 45, true,
                "https://m.media-amazon.com/images/I/91b0C2YNSrL._AC_UF1000,1000_QL80_.jpg");
        l3.save();

        Libro l4 = new Libro(null, "Alfaguara", 500, "Cien Años de Soledad", "Gabriel García Márquez", "Realismo Mágico", 18, true,
                "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1327813613i/15544.jpg");
        l4.save();

        // --- 3. Préstamos Iniciales ---
        // Prestamo(Integer id, int id_libro, String nif, String fecha_inicio, String fecha_fin, boolean devuelto)
        Prestamo p1 = new Prestamo(null, l1.getId_libro(), "12345678A", "01/03/2026", "31/03/2026", false);
        p1.save();

        Prestamo p2 = new Prestamo(null, l2.getId_libro(), "87654321B", "10/03/2026", "10/04/2026", false);
        p2.save();
    }
}
