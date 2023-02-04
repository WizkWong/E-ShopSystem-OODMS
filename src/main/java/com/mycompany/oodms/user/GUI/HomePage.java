/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.oodms.user.GUI;

import com.mycompany.oodms.OODMS;

/**
 *
 * @author Wong Chi Jian
 */
public class HomePage extends javax.swing.JPanel {

    /**
     * Creates new form HomePage
     */
    public HomePage() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleLb = new javax.swing.JLabel();
        loginBtt = new javax.swing.JButton();
        registerBtt = new javax.swing.JButton();
        productBtt = new javax.swing.JButton();
        exitBtt = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(600, 300));
        setPreferredSize(new java.awt.Dimension(600, 300));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titleLb.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        titleLb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLb.setText("Welcome to XXX");
        add(titleLb, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, -1, -1));

        loginBtt.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        loginBtt.setText("Login");
        loginBtt.setFocusable(false);
        loginBtt.setPreferredSize(new java.awt.Dimension(200, 50));
        loginBtt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBttActionPerformed(evt);
            }
        });
        add(loginBtt, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, -1, -1));

        registerBtt.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        registerBtt.setText("Register");
        registerBtt.setFocusable(false);
        registerBtt.setPreferredSize(new java.awt.Dimension(200, 50));
        registerBtt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerBttActionPerformed(evt);
            }
        });
        add(registerBtt, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 110, -1, -1));

        productBtt.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        productBtt.setText("Our Product");
        productBtt.setFocusable(false);
        productBtt.setPreferredSize(new java.awt.Dimension(200, 50));
        productBtt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productBttActionPerformed(evt);
            }
        });
        add(productBtt, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, -1, -1));

        exitBtt.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        exitBtt.setText("Exit");
        exitBtt.setFocusable(false);
        exitBtt.setMargin(new java.awt.Insets(0, 0, 0, 0));
        exitBtt.setPreferredSize(new java.awt.Dimension(200, 50));
        exitBtt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBttActionPerformed(evt);
            }
        });
        add(exitBtt, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 250, 70, 40));
    }// </editor-fold>//GEN-END:initComponents

    private void loginBttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBttActionPerformed
        OODMS.frame.refresh(new LoginPage());
    }//GEN-LAST:event_loginBttActionPerformed

    private void registerBttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerBttActionPerformed
        OODMS.frame.refresh(new RegisterPage());
    }//GEN-LAST:event_registerBttActionPerformed

    private void productBttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productBttActionPerformed
        OODMS.frame.refresh(new ProductPage());
    }//GEN-LAST:event_productBttActionPerformed

    private void exitBttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBttActionPerformed
        OODMS.frame.dispose();
    }//GEN-LAST:event_exitBttActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exitBtt;
    private javax.swing.JButton loginBtt;
    private javax.swing.JButton productBtt;
    private javax.swing.JButton registerBtt;
    private javax.swing.JLabel titleLb;
    // End of variables declaration//GEN-END:variables
}
