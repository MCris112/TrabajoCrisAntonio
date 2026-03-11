package com.trabajocrisantonio.modelos;

import com.darkredgm.querymc.Annotations.Column;
import com.darkredgm.querymc.Annotations.Primary;
import com.darkredgm.querymc.Database.Model;

public class Prestamo extends Model {

    public Prestamo(){

    }
    public Prestamo(int id, int id_libro, String nif, String fecha_inicio, String fecha_fin, boolean devuelto) {
        this.id_libro = id_libro;
        this.id = id;
        this.nif = nif;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.devuelto = devuelto;
    }

    @Primary
    @Column
    private int id;

    @Column
    //Clave foranea
    private int id_libro;

    @Column
    //Clave foranea
    private String nif;

    @Column
    private String fecha_inicio;

    @Column
    private String fecha_fin;

    @Column
    private boolean devuelto;

    public int getId_libro() {
        return id_libro;
    }

    public void setId_libro(int id_libro) {
        this.id_libro = id_libro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public boolean isDevuelto() {
        return devuelto;
    }

    public void setDevuelto(boolean devuelto) {
        this.devuelto = devuelto;
    }
}


