package com.trabajocrisantonio.Componentes;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;
import java.util.Calendar;

/**
 * Componente de texto formateado con estilo Material Design.
 * Permite usar máscaras como "##/##/####" para fechas con auto-ajuste.
 */
public class MatFechaField extends JFormattedTextField {

    public MatFechaField() {
        super(createMask("##/##/####"));
        initStyles();
    }

    /**
     * Crea la máscara de entrada (ej: ##/##/####)
     */
    private static MaskFormatter createMask(String mask) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(mask);
            formatter.setPlaceholderCharacter('_');
            formatter.setAllowsInvalid(false);
            formatter.setOverwriteMode(true);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatter;
    }

    private void initStyles() {
        setPreferredSize(new Dimension(200, 35));
        setFont(new Font("Roboto", Font.PLAIN, 14));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        setBackground(Color.WHITE);
        setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);

        // --- Lógica de Auto-ajuste de Fecha ---
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                ajustarFecha();
            }
        });
    }


    private void ajustarFecha() {
        String texto = getText();
        // Si la fecha no está completa (contiene guiones), no ajustamos
        if (texto.contains("_")) return;

        try {
            String[] partes = texto.split("/");
            if (partes.length != 3) return;

            int dia = Integer.parseInt(partes[0]);
            int mes = Integer.parseInt(partes[1]);
            int año = Integer.parseInt(partes[2]);

            // 1. Ajustar Mes (máximo 12)
            if (mes > 12) mes = 12;
            if (mes < 1) mes = 1;

            // 2. Ajustar Día según el mes y año (bisiestos incluidos)
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, año);
            cal.set(Calendar.MONTH, mes - 1); // Calendar usa 0-11
            cal.set(Calendar.DAY_OF_MONTH, 1);
            
            int maxDia = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            if (dia > maxDia) dia = maxDia;
            if (dia < 1) dia = 1;

            // Formatear de nuevo con ceros a la izquierda si es necesario
            String nuevaFecha = String.format("%02d/%02d/%04d", dia, mes, año);
            setText(nuevaFecha);
            
        } catch (Exception ex) {
            // Si hay un error al parsear, dejamos que JFormattedTextField maneje el revert
        }
    }
}
