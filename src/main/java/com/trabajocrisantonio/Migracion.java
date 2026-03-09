package com.trabajocrisantonio;

import java.sql.SQLException;

import com.darkredgm.querymc.Database.DB;
import com.trabajocrisantonio.modelos.Libro;
import com.trabajocrisantonio.modelos.Prestamo;
import com.trabajocrisantonio.modelos.Usuario;

public class Migracion {
    public static void main(String[] args) throws SQLException {
        DB.createDatabase("tareaDomingo");
        DB.verify(Libro.class, Usuario.class, Prestamo.class);
    }
}
