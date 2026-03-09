package com.trabajocrisantonio.modelos;

import com.darkredgm.querymc.Annotations.Column;
import com.darkredgm.querymc.Annotations.Primary;

public class Usuario {
   
    @Column
    private String nombre;
    
    @Primary
    @Column
    private String nif;

    @Column
    private String apellido1;

    @Column
    private String apellido2;

    @Column
    private String direccion;

    @Column
    private String telefono;

}
