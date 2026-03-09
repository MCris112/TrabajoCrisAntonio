package Modelos;

import com.darkredgm.querymc.Annotations.DBColPrimary;
import com.darkredgm.querymc.Annotations.DbColumn;

public class libro {
    @DBColPrimary
    @DbColumn
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
