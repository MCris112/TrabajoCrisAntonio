package com.trabajocrisantonio.Controllers;

import Models.Pieza;
import Views.BaseView;
import com.darkredgm.querymc.Database.Model;

public class PiezaController extends BaseController{

    public PiezaController(BaseView view) {
        super(view);
    }

    @Override
    public Model getModel() {
        return new Pieza();
    }
}
