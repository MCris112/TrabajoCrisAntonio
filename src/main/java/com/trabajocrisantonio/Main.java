package com.trabajocrisantonio;

import com.trabajocrisantonio.Views.SeleccionarVista;

public class Main {


    public static void main(String[] args) {
        // Cargar migraciones


        // Mostrar el cargador
        Loader loader = new Loader();

        // Esperar 2 segundos
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Esconder el cargador y mostrar la aplicación
        loader.setVisible(false);
        loader.dispose();
        
        // Cargar app
        new SeleccionarVista();
    }
}
