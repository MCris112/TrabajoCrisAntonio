package com.trabajocrisantonio.Views;

import Models.Proveedor;
import com.darkredgm.querymc.Database.Model;

public class ProveedorView extends BaseView {
    @Override
    public Model getModel() {
        return new Proveedor();
    }
}
