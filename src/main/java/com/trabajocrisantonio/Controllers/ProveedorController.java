package com.trabajocrisantonio.Controllers;

import Models.Proveedor;
import Views.BaseView;
import com.darkredgm.querymc.Database.Model;

public class ProveedorController extends BaseController{

    public ProveedorController(BaseView view) {
        super(view);
    }

    @Override
    public Model getModel() {
        return new Proveedor();
    }
}
