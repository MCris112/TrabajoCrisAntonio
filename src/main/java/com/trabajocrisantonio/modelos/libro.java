package com.trabajocrisantonio.modelos;

import com.darkredgm.querymc.Annotations.Column;
import com.darkredgm.querymc.Annotations.Primary;

public class libro {
    @Primary
    @Column
    private int id_libro;

    @DbColumn
    private String editorial;

    @DbColumn
    private int numero_hojas;

    @DbColumn
    private String titulo;

    @DbColumn
    private String autor;

    @DbColumn
    private String genero;

    @DbColumn
    private double precio;

    @DbColumn
    private boolean bestseller;

}
