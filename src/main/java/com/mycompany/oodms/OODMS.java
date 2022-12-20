/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.oodms;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Wong Chi Jian
 */
public class OODMS extends JFrame{
    private final JPanel start;

    public OODMS() {
        start = new TestJPanel();
        this.setContentPane(start);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
        
    }

    public static void main(String[] args) {
        new OODMS();
    }
}
