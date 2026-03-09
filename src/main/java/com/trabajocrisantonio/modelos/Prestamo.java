package com.trabajocrisantonio.modelos;

import com.darkredgm.querymc.Annotations.Column;

public class Prestamo {
  
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


