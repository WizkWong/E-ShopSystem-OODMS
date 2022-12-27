/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.oodms;



import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.*;
import java.util.*;

/**
 *
 * @author Wong Chi Jian
 */
public class OODMS extends JFrame{
    // main frame
    static JFrame frame;

    public OODMS(JPanel startingPanel) {
        this.add(startingPanel); // start up JPanel
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
    }

    public void refresh(JPanel oldPanel, JPanel newPanel) {
        this.remove(oldPanel);
        this.add(newPanel);
        this.pack();
    }

    public static void main(String[] args) {
        frame = new OODMS(new TestJPanel());
    }
}
