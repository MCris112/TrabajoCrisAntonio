package com.trabajocrisantonio.modelos;

import com.darkredgm.querymc.Annotations.Column;
import com.darkredgm.querymc.Annotations.Primary;
import com.darkredgm.querymc.Database.Model;

public class Libro extends Model {

    public Libro(){}

    public Libro(int id_libro, String editorial, int numero_hojas, String titulo, String autor, String genero, double precio, boolean bestseller) {
        this.idLibro = idLibro;
        this.editorial = editorial;
        this.numero_hojas = numero_hojas;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.precio = precio;
        this.bestseller = bestseller;
    }

    @Primary
    @Column(nullable = false)
    private int idLibro;

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

    public int getId_libro() {
        return idLibro;
    }

    public void setId_libro(int id_libro) {
        this.idLibro = id_libro;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getNumero_hojas() {
        return numero_hojas;
    }

    public void setNumero_hojas(int numero_hojas) {
        this.numero_hojas = numero_hojas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isBestseller() {
        return bestseller;
    }

    public void setBestseller(boolean bestseller) {
        this.bestseller = bestseller;
    }
}
