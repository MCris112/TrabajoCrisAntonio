package com.trabajocrisantonio.modelos;

import com.darkredgm.querymc.Annotations.Column;
import com.darkredgm.querymc.Annotations.Primary;
import com.darkredgm.querymc.Database.Model;

public class Usuario extends Model {
   
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
