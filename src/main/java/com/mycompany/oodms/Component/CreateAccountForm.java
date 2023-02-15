package com.mycompany.oodms.Component;

import com.mycompany.oodms.OODMS;
import com.mycompany.oodms.admin.Admin;
import com.mycompany.oodms.deliveryStaff.DeliveryStaff;

import javax.swing.*;
import java.awt.*;

public class CreateAccountForm extends javax.swing.JPanel {

    /**
     * Creates new form AddAccountForm
     */
    public CreateAccountForm() {
        initComponents();
        cfPssMsgLb.setVisible(false);
        emailMsgLb.setVisible(false);
        phoneNoMsgLb.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usernameLb = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        pssLb = new javax.swing.JLabel();
        pssField1 = new javax.swing.JPasswordField();
        cfPssLb = new javax.swing.JLabel();
        pssField2 = new javax.swing.JPasswordField();
        emailLb = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        PhoneNoLb = new javax.swing.JLabel();
        phoneNoField = new com.mycompany.oodms.Component.JNumberField();
        roleLb = new javax.swing.JLabel();
        roleCB = new javax.swing.JComboBox<>();
        usernameMsgLb = new javax.swing.JLabel();
        pssMsgLb = new javax.swing.JLabel();
        cfPssMsgLb = new javax.swing.JLabel();
        emailMsgLb = new javax.swing.JLabel();
        phoneNoMsgLb = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(540, 380));
        setPreferredSize(new java.awt.Dimension(540, 380));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        usernameLb.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        usernameLb.setText("Username:");
        add(usernameLb, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 150, 30));

        usernameField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        usernameField.setToolTipText("");
        usernameField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 153, 255), 2, true));
        add(usernameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 340, 30));

        pssLb.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        pssLb.setText("Password:");
        add(pssLb, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 150, 30));

        pssField1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 153, 255), 2, true));
        add(pssField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 340, 30));

        cfPssLb.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cfPssLb.setText("Confirm Password:");
        add(cfPssLb, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 160, 30));

        pssField2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 153, 255), 2, true));
        add(pssField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 340, 30));

        emailLb.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        emailLb.setText("Email:");
        add(emailLb, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 150, 30));

        emailField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        emailField.setToolTipText("");
        emailField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 153, 255), 2, true));
        add(emailField, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 190, 340, 30));

        PhoneNoLb.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        PhoneNoLb.setText("Phone Number:");
        add(PhoneNoLb, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 150, 30));

        phoneNoField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 153, 255), 2, true));
        add(phoneNoField, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, 340, 30));

        roleLb.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        roleLb.setText("Role:");
        add(roleLb, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 150, 30));

        roleCB.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        roleCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Delivery Staff" }));
        add(roleCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 310, 340, 30));

        usernameMsgLb.setText("Username must be at least 4 characters");
        add(usernameMsgLb, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 340, 20));

        pssMsgLb.setText("Password length must be at least 8");
        add(pssMsgLb, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 340, 20));

        cfPssMsgLb.setForeground(java.awt.Color.red);
        cfPssMsgLb.setText("Confirm Password is not same as Password!");
        add(cfPssMsgLb, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, 340, 20));

        emailMsgLb.setForeground(java.awt.Color.red);
        emailMsgLb.setText("Email is invalid!");
        add(emailMsgLb, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, 340, 20));

        phoneNoMsgLb.setForeground(java.awt.Color.red);
        phoneNoMsgLb.setText("Phone Number is invalid!");
        add(phoneNoMsgLb, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 280, 340, 20));
    }// </editor-fold>//GEN-END:initComponents

    public boolean formValidate() {
        String username = usernameField.getText();
        String pss1 = new String(pssField1.getPassword());
        String pss2 = new String(pssField2.getPassword());
        String email = emailField.getText();
        String phoneNo = phoneNoField.getText();
        String role = (String) roleCB.getSelectedItem();

        String errMsg = "";
        if (role.equals("Admin")) {
            errMsg += Admin.createAcc(username, pss1, pss2, email, phoneNo);
        } else if (role.equals("Delivery Staff")) {
            errMsg += DeliveryStaff.createAcc(username, pss1, pss2, email, phoneNo);
        }

        if (errMsg.isEmpty()) {
            return true;
        }

        usernameMsgLb.setText("Username must be at least 4 characters");
        usernameMsgLb.setForeground(Color.BLACK);
        usernameField.setBorder(BorderFactory.createLineBorder(new Color(51,153,255), 2, true));

        pssMsgLb.setText("Password length must be at least 8");
        pssMsgLb.setForeground(Color.BLACK);
        pssField1.setBorder(BorderFactory.createLineBorder(new Color(51,153,255), 2, true));

        cfPssMsgLb.setVisible(false);
        pssField2.setBorder(BorderFactory.createLineBorder(new Color(51,153,255), 2, true));

        emailMsgLb.setVisible(false);
        emailField.setBorder(BorderFactory.createLineBorder(new Color(51,153,255), 2, true));

        phoneNoMsgLb.setVisible(false);
        phoneNoField.setBorder(BorderFactory.createLineBorder(new Color(51,153,255), 2, true));

        if (errMsg.contains("System Error")) {
            OODMS.showErrorMessage();
            return false;
        }

        if (errMsg.contains("Username character less than 4")) {
            usernameMsgLb.setText("Username invalid, username must be at least 4 characters!");
            usernameMsgLb.setForeground(Color.RED);
            usernameField.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));

        } else if (errMsg.contains("Username taken")) {
            usernameMsgLb.setText("Username has been taken!");
            usernameMsgLb.setForeground(Color.RED);
            usernameField.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
        }

        if (errMsg.contains("Password length less than 8")) {
            pssMsgLb.setText("Password invalid, password length must be at least 8!");
            pssMsgLb.setForeground(Color.RED);
            pssField1.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));

        } else if (errMsg.contains("Password length is not same")) {
            cfPssMsgLb.setVisible(true);
            pssField2.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
        }

        if (errMsg.contains("Email is invalid")) {
            emailMsgLb.setVisible(true);
            emailField.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
        }

        if (errMsg.contains("Phone number is invalid")) {
            phoneNoMsgLb.setVisible(true);
            phoneNoField.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
        }
        return false;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel PhoneNoLb;
    private javax.swing.JLabel cfPssLb;
    private javax.swing.JLabel cfPssMsgLb;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel emailLb;
    private javax.swing.JLabel emailMsgLb;
    private com.mycompany.oodms.Component.JNumberField phoneNoField;
    private javax.swing.JLabel phoneNoMsgLb;
    private javax.swing.JPasswordField pssField1;
    private javax.swing.JPasswordField pssField2;
    private javax.swing.JLabel pssLb;
    private javax.swing.JLabel pssMsgLb;
    private javax.swing.JComboBox<String> roleCB;
    private javax.swing.JLabel roleLb;
    private javax.swing.JTextField usernameField;
    private javax.swing.JLabel usernameLb;
    private javax.swing.JLabel usernameMsgLb;
    // End of variables declaration//GEN-END:variables
}