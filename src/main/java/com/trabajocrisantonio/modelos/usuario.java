package com.trabajocrisantonio.modelos;

public class usuario {
   
    @DbColumn
    private String nombre;
    
    @DBColPrimary
    @DbColumn
    private String nif;

    @DbColumn
    private String apellido1;

    @DbColumn
    private String apellido2;

    @DbColumn
    private String direccion;

    @DbColumn
    private String telefono;

}
