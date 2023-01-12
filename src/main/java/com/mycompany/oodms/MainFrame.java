package com.mycompany.oodms;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

public class MainFrame extends JFrame {

    public JPanel currentPanel;

    public MainFrame(JPanel startingPanel) {
        this.currentPanel = startingPanel;
        this.currentPanel.setBackground(new Color(197,233,245));
        this.setLayout(new BorderLayout());
        this.add(startingPanel); // start up JPanel
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public void refresh(JPanel newPanel) {
        // Remove old panel
        this.remove(currentPanel);
        // Change currentPanel variable to the current panel
        this.currentPanel = newPanel;
        // Add new panel in
        this.add(newPanel, BorderLayout.CENTER);
        // Setting background colour
        newPanel.setBackground(new Color(197,233,245));
        // To refresh the JPanel to adjust the panel size and change the element
        this.pack();
        // Set panel position to middle
        this.setLocationRelativeTo(null);
    }
}
