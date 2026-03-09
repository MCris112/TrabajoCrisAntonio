package com.trabajocrisantonio.modelos;

import com.darkredgm.querymc.Annotations.Column;
import com.darkredgm.querymc.Annotations.Primary;
import com.darkredgm.querymc.Database.Model;

public class Libro extends Model {
    @Primary
    @Column
    private int id_libro;

    @Column
    private String editorial;

    @Column
    private int numero_hojas;

    @Column
    private String titulo;

    @Column
    private String autor;

    @Column
    private String genero;

    @Column
    private double precio;

    @Column
    private boolean bestseller;

}
