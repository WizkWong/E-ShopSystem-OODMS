package com.mycompany.oodms.Component;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JNumberField extends JTextField {

    public JNumberField() {
        super();
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char n = e.getKeyChar();
                if (!Character.isDigit(n)) {
                    e.consume();
                }
            }
        });
    }

    public int getInteger() {
        return Integer.parseInt(this.getText());
    }
}
