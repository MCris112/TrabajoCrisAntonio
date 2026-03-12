package com.trabajocrisantonio.modelos;

import com.darkredgm.querymc.Annotations.Column;
import com.darkredgm.querymc.Annotations.Primary;
import com.darkredgm.querymc.Database.Model;

public class Prestamo extends Model {

    public Prestamo(){

    }
    public Prestamo(Integer id, int id_libro, String nif, String fecha_inicio, String fecha_fin, boolean devuelto) {
        this.idLibro = id_libro;
        this.id = id;
        this.nif = nif;
        this.fechaInicio = fecha_inicio;
        this.fechaFin = fecha_fin;
        this.devuelto = devuelto;
    }

    @Primary
    @Column
    private Integer id;

    @Column
    //Clave foranea
    private int idLibro;

    @Column
    //Clave foranea
    private String nif;

    @Column
    private String fechaInicio;

    @Column
    private String fechaFin;

    @Column
    private boolean devuelto;

    public int getId_libro() {
        return idLibro;
    }

    public void setId_libro(int id_libro) {
        this.idLibro = id_libro;
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
        return fechaInicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fechaInicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fechaFin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fechaFin = fecha_fin;
    }

    public boolean isDevuelto() {
        return devuelto;
    }

    public void setDevuelto(boolean devuelto) {
        this.devuelto = devuelto;
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "id=" + id +
                ", id_libro=" + idLibro +
                ", nif='" + nif + '\'' +
                ", fecha_inicio='" + fechaInicio + '\'' +
                ", fecha_fin='" + fechaFin + '\'' +
                ", devuelto=" + devuelto +
                '}';
    }
}


