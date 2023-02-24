/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.oodms.Component;

import com.mycompany.oodms.OODMS;
import com.mycompany.oodms.admin.GUI.CategoryManagementPage;
import com.mycompany.oodms.item.Item;
import com.mycompany.oodms.item.ItemDao;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

public class EditCategoryForm extends javax.swing.JPanel {

    private final ItemDao itemDao;
    
    public EditCategoryForm(long CatID) {
        itemDao = OODMS.getItemDao();
        initComponents();
        
        // Get category name using the id
        String CatName = itemDao.getCategoryNameById(CatID);
        
        // Set category name
        CatNameTxt.setText(CatName);
        
        // Set error message not visible
        CatErrLab.setVisible(false);
    }

    public boolean formValidate(long CatID) {
        // Get category name using the id
        String CatName = itemDao.getCategoryNameById(CatID);
        
        // Get new category name
        String NewCatName = CatNameTxt.getText();
        
        
        // Check if category name field is empty
        if (NewCatName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in the category name field !", "Error", JOptionPane.ERROR_MESSAGE);
            CatErrLab.setVisible(false);
            CatNameTxt.setBorder(BorderFactory.createLineBorder(new Color(51, 153, 255), 2, true));
            return false;
        } else if (CatName.equals(NewCatName)) {
            JOptionPane.showMessageDialog(null, "Category name remain unchanged");
            return true;
        }
        
        String errorMessage = Item.validateItem("ProductNameGood", NewCatName);
        
        if (errorMessage.isEmpty()) {
            // Modify Caategory
            itemDao.modifyCategory(CatID, NewCatName);
            OODMS.frame.refresh(new CategoryManagementPage());     
            
            JOptionPane.showMessageDialog(null, "Successfully Updated Category", "Success", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }
        
        if (errorMessage.contains("ProCat_Err1")) {
            CatErrLab.setText("Category already exist !");
            CatErrLab.setVisible(true);
            CatNameTxt.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
        } else if (errorMessage.contains("ProCat_Err2")) {
            CatErrLab.setText("Invalid category !");
            CatErrLab.setVisible(true);
            CatNameTxt.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
        } else {
            CatErrLab.setVisible(false);
            CatNameTxt.setBorder(BorderFactory.createLineBorder(new Color(51, 153, 255), 2, true));
        }
        
        return false;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TitleLab = new javax.swing.JLabel();
        CatNameLab = new javax.swing.JLabel();
        CatNameTxt = new javax.swing.JTextField();
        CatErrLab = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(375, 150));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TitleLab.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        TitleLab.setText("Edit Category Form");
        add(TitleLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        CatNameLab.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        CatNameLab.setText("Category Name:");
        add(CatNameLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 140, 30));

        CatNameTxt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        CatNameTxt.setToolTipText("");
        CatNameTxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 153, 255), 2, true));
        add(CatNameTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 190, 30));

        CatErrLab.setBackground(new java.awt.Color(240, 240, 240));
        CatErrLab.setForeground(new java.awt.Color(255, 0, 51));
        CatErrLab.setText("Category already exist !");
        CatErrLab.setBorder(null);
        add(CatErrLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 140, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CatErrLab;
    private javax.swing.JLabel CatNameLab;
    private javax.swing.JTextField CatNameTxt;
    private javax.swing.JLabel TitleLab;
    // End of variables declaration//GEN-END:variables
}