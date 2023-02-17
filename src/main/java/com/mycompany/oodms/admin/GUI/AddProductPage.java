package com.mycompany.oodms.admin.GUI;

import com.mycompany.oodms.OODMS;
import com.mycompany.oodms.item.Item;
import com.mycompany.oodms.item.ItemDao;

import java.awt.Color;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

public class AddProductPage extends javax.swing.JPanel {

    private final ItemDao itemDao;

    public AddProductPage() {
        itemDao = OODMS.getItemDao();
        initComponents();
        ProCatRad1.setSelected(true);
        ProCatTxt.setEnabled(false);
        NameErrLab.setVisible(false);
        CatErrLab.setVisible(false);
        
        // Adding existing category to combo box
        List<String> allCategory = itemDao.getAllCategoryName();
        
        // If there is no existing category, combo box will be disable
        if (allCategory.isEmpty()) {
            ProCatRad1.setEnabled(false);
            ProCatRad2.setSelected(true);
            ProCatTxt.setBorder(BorderFactory.createLineBorder(new Color(51, 153, 255), 2, true));
            ProCatCom.setForeground(new Color(183, 206, 233));
        }
        
        for (String category : allCategory) {
            ProCatCom.addItem(category);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        AddProBut = new javax.swing.JButton();
        TitleLab = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        ProDesLab = new javax.swing.JLabel();
        ProDesTxt = new javax.swing.JTextField();
        ProNameLab = new javax.swing.JLabel();
        ProNameTxt = new javax.swing.JTextField();
        ProCatLab = new javax.swing.JLabel();
        ProPriceLab = new javax.swing.JLabel();
        ProStockLab = new javax.swing.JLabel();
        ProCatTxt = new javax.swing.JTextField();
        ProPriceTxt = new javax.swing.JTextField();
        ProStockTxt = new javax.swing.JTextField();
        CatErrLab = new javax.swing.JTextField();
        NameErrLab = new javax.swing.JTextField();
        ProCatCom = new javax.swing.JComboBox<>();
        ProCatRad2 = new javax.swing.JRadioButton();
        ProCatRad1 = new javax.swing.JRadioButton();
        BackBut = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(600, 452));
        setPreferredSize(new java.awt.Dimension(600, 452));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AddProBut.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        AddProBut.setText("Add Product");
        AddProBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddProButActionPerformed(evt);
            }
        });
        add(AddProBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 390, 160, 40));

        TitleLab.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        TitleLab.setText("Add Data Form");
        add(TitleLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ProDesLab.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ProDesLab.setText("Description:");
        jPanel1.add(ProDesLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 120, 30));

        ProDesTxt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ProDesTxt.setToolTipText("");
        ProDesTxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 153, 255), 2, true));
        ProDesTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProDesTxtActionPerformed(evt);
            }
        });
        jPanel1.add(ProDesTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, 340, 30));

        ProNameLab.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ProNameLab.setText("Product Name:");
        jPanel1.add(ProNameLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 130, 30));

        ProNameTxt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ProNameTxt.setToolTipText("");
        ProNameTxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 153, 255), 2, true));
        jPanel1.add(ProNameTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 340, 30));

        ProCatLab.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ProCatLab.setText("Category:");
        jPanel1.add(ProCatLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 130, 30));

        ProPriceLab.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ProPriceLab.setText("Price:");
        jPanel1.add(ProPriceLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 130, 30));

        ProStockLab.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ProStockLab.setText("Stock:");
        jPanel1.add(ProStockLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 130, 30));

        ProCatTxt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ProCatTxt.setToolTipText("");
        ProCatTxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));
        jPanel1.add(ProCatTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, 140, 30));

        ProPriceTxt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ProPriceTxt.setToolTipText("");
        ProPriceTxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 153, 255), 2, true));
        jPanel1.add(ProPriceTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 340, 30));

        ProStockTxt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ProStockTxt.setToolTipText("");
        ProStockTxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 153, 255), 2, true));
        jPanel1.add(ProStockTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, 340, 30));

        CatErrLab.setForeground(new java.awt.Color(255, 0, 51));
        CatErrLab.setText("Category already exist !");
        CatErrLab.setBorder(null);
        CatErrLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CatErrLabActionPerformed(evt);
            }
        });
        jPanel1.add(CatErrLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 110, 140, -1));

        NameErrLab.setForeground(new java.awt.Color(255, 0, 51));
        NameErrLab.setText("Product name is invalid !");
        NameErrLab.setBorder(null);
        NameErrLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NameErrLabActionPerformed(evt);
            }
        });
        jPanel1.add(NameErrLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 340, -1));

        jPanel1.add(ProCatCom, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 120, 30));

        ProCatRad2.setBackground(new java.awt.Color(255, 255, 255));
        ProCatRad2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProCatRad2ActionPerformed(evt);
            }
        });
        jPanel1.add(ProCatRad2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 80, -1, 30));

        ProCatRad1.setBackground(new java.awt.Color(255, 255, 255));
        ProCatRad1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProCatRad1ActionPerformed(evt);
            }
        });
        jPanel1.add(ProCatRad1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 20, 30));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 540, 310));

        BackBut.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BackBut.setText("Back");
        BackBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButActionPerformed(evt);
            }
        });
        add(BackBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void AddProButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddProButActionPerformed
        // Getting data from add product form
        String ProName = ProNameTxt.getText();
        String ProCat = "";
        String ProPriceS = ProPriceTxt.getText();
        String ProStockS = ProStockTxt.getText();
        String ProDes = ProDesTxt.getText();
        if (ProCatRad1.isSelected()) {
            // Getting all the category data to search and append existing category id for the item
            ProCat = (String) ProCatCom.getSelectedItem();
            ProCat = itemDao.getCategoryIdByName(ProCat);

        } else {
            ProCat = ProCatTxt.getText();
        }
        
        String CheckEmpty = Item.validateEmpty(ProName, ProCat, ProPriceS, ProStockS, ProDes);
        if (CheckEmpty.equals("empty")) {
            JOptionPane.showMessageDialog(this, "Please fill in all the text field !");
            return;
        }
        
        double ProPrice = Double.parseDouble(ProPriceS);
        int ProStock = Integer.parseInt(ProStockS);
        String errorMessage = Item.addItem(ProName, ProCat, ProPrice, ProStock, ProDes);
        System.out.println(errorMessage);

        if (errorMessage.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Product has been added");
            OODMS.frame.refresh(new AdminPanelForm());
            return;
        }
        
        if (errorMessage.contains("ProName_Err")) {
            NameErrLab.setVisible(true);
            ProNameTxt.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
        } else {
            NameErrLab.setVisible(false);
            ProNameTxt.setBorder(BorderFactory.createLineBorder(new Color(51, 153, 255), 2, true));
        }
        
        if (ProCatRad2.isSelected()) {
            if (errorMessage.contains("ProCat_Err1")) {
                CatErrLab.setText("Category already exist !");
                CatErrLab.setVisible(true);
                ProCatTxt.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
            } else if (errorMessage.contains("ProCat_Err2")) {
                CatErrLab.setText("Invalid category !");
                CatErrLab.setVisible(true);
                ProCatTxt.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
            } else {
                CatErrLab.setVisible(false);
                ProCatTxt.setBorder(BorderFactory.createLineBorder(new Color(51, 153, 255), 2, true));
            }
        }
    }//GEN-LAST:event_AddProButActionPerformed

    private void ProDesTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProDesTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ProDesTxtActionPerformed

    private void CatErrLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CatErrLabActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CatErrLabActionPerformed

    private void NameErrLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NameErrLabActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NameErrLabActionPerformed

    private void BackButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButActionPerformed
        OODMS.frame.refresh(new AdminPanelForm());
    }//GEN-LAST:event_BackButActionPerformed

    private void ProCatRad1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProCatRad1ActionPerformed
        if (ProCatRad1.isSelected()) {
            ProCatRad2.setSelected(false);
            ProCatTxt.setEnabled(false);
            ProCatCom.setEnabled(true);
            ProCatTxt.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204), 2, true));
            ProCatCom.setForeground(new Color(0, 0, 0));
            CatErrLab.setVisible(false);
        } else {
            ProCatRad1.setSelected(true);
        }     
    }//GEN-LAST:event_ProCatRad1ActionPerformed

    private void ProCatRad2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProCatRad2ActionPerformed
        if (ProCatRad2.isSelected()) {           
            ProCatRad1.setSelected(false);
            ProCatCom.setEnabled(false);
            ProCatTxt.setEnabled(true);
            ProCatTxt.setBorder(BorderFactory.createLineBorder(new Color(51, 153, 255), 2, true));
            ProCatCom.setForeground(new Color(183, 206, 233));
        } else {
            ProCatRad2.setSelected(true);
        }        
    }//GEN-LAST:event_ProCatRad2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddProBut;
    private javax.swing.JButton BackBut;
    private javax.swing.JTextField CatErrLab;
    private javax.swing.JTextField NameErrLab;
    private javax.swing.JComboBox<String> ProCatCom;
    private javax.swing.JLabel ProCatLab;
    private javax.swing.JRadioButton ProCatRad1;
    private javax.swing.JRadioButton ProCatRad2;
    private javax.swing.JTextField ProCatTxt;
    private javax.swing.JLabel ProDesLab;
    private javax.swing.JTextField ProDesTxt;
    private javax.swing.JLabel ProNameLab;
    private javax.swing.JTextField ProNameTxt;
    private javax.swing.JLabel ProPriceLab;
    private javax.swing.JTextField ProPriceTxt;
    private javax.swing.JLabel ProStockLab;
    private javax.swing.JTextField ProStockTxt;
    private javax.swing.JLabel TitleLab;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
