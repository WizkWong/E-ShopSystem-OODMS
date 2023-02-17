package com.mycompany.oodms.Component;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JCurrencyField extends JTextField {

    public JCurrencyField() {
        super();
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char n = e.getKeyChar();
                if ((!Character.isDigit(n)) && n != '.') {
                    e.consume();
                }
            }
        });
    }

    public Double getCurrency() {
        try {
            return Double.valueOf(this.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }
}
