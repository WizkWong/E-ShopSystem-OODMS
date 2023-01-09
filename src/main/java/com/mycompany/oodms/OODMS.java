/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.oodms;


import com.mycompany.oodms.customer.Customer;
import com.mycompany.oodms.item.Item;
import com.mycompany.oodms.user.User;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class OODMS extends JFrame{
    // main frame
    public static JFrame frame;

    public OODMS(JPanel startingPanel) {
        this.add(startingPanel); // start up JPanel
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
    }

    public void refresh(JPanel oldPanel, JPanel newPanel) {
        this.remove(oldPanel);
        this.add(newPanel);
        this.revalidate();
        this.repaint();
    }

    public static void initialize() {
        FileService.createFile(User.USER_FILENAME);
        FileService.createFile(Customer.FILENAME);
        FileService.createFile(Item.FILENAME);
        FileService.createFile(Customer.CART_FILENAME);
    }

    public static void main(String[] args) {
        initialize();
        frame = new OODMS(new TestJPanel());
    }
}
