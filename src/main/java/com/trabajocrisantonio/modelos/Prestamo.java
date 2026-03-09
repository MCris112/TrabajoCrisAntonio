package com.trabajocrisantonio.modelos;

import com.darkredgm.querymc.Annotations.Column;
import com.darkredgm.querymc.Database.Model;

public class Prestamo extends Model {
  
    @Column
    //Clave foranea
    private int id_libro;


    @Column
    private int numero_prestamo;

    @Column
    //Clave foranea
    private String nif;

    @Column
    private String fecha_inicio;

    @Column
    private String fecha_fin;

    @Column
    private boolean devuelto;

}


