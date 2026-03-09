package main.java.Modelos;

public class prestamo {
  
    @DbColumn
    //Clave foranea
    private int id_libro;

    @DbColumn
    private int numero_prestamo;

    @DbColumn
    //Clave foranea
    private String nif;

    @DbColumn
    private String fecha_inicio;

    @DbColumn
    private String fecha_fin;

    @DbColumn
    private boolean devuelto;

}


