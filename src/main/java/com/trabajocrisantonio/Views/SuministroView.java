package com.trabajocrisantonio.Views;

import Models.Suministro;
import com.darkredgm.querymc.Database.Model;

public class SuministroView extends BaseView {


    @Override
    public Model getModel() {
        return new Suministro();
    }
}
