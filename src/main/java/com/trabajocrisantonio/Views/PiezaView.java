package com.trabajocrisantonio.Views;

import Models.Pieza;
import com.darkredgm.querymc.Database.Model;

public class PiezaView extends BaseView {
    @Override
    public Model getModel() {
        return new Pieza();
    }
}
