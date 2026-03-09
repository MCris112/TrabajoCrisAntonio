package com.trabajocrisantonio.Controllers;

import Models.Suministro;
import Views.BaseView;
import com.darkredgm.querymc.Database.Model;

public class SuministroController extends BaseController{

    public SuministroController(BaseView view) {
        super(view);
    }

    @Override
    public Model getModel() {
        return new Suministro();
    }
}
