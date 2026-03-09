package com.trabajocrisantonio.Views;

import Models.Categoria;
import com.darkredgm.querymc.Database.Model;

public class CategoriaView extends BaseView {


    @Override
    public Model getModel() {
        return new Categoria();
    }
}
