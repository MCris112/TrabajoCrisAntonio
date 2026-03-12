package com.trabajocrisantonio;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SoloNumerosAdapter extends KeyAdapter {

    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        // Si el carácter no es un número, ignorar el evento
        if (!Character.isDigit(c)) {
            e.consume();
        }
    }
}
