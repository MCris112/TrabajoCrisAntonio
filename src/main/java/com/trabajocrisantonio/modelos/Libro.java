package com.trabajocrisantonio.modelos;

import com.darkredgm.querymc.Annotations.Column;
import com.darkredgm.querymc.Annotations.Primary;

public class Libro {
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
