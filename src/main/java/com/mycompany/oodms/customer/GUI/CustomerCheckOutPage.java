package com.mycompany.oodms.customer.GUI;

import com.mycompany.oodms.OODMS;
import com.mycompany.oodms.customer.CartItem;
import com.mycompany.oodms.customer.Customer;
import com.mycompany.oodms.item.Item;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.util.List;

public class CustomerCheckOutPage extends javax.swing.JPanel {

    private final DefaultTableModel productTableModel;

    /**
     * Creates new form CustomerCheckOutPage
     */
    public CustomerCheckOutPage() {
        initComponents();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);

        TableColumnModel productTableColumnModel = productTable.getColumnModel();
        productTableColumnModel.getColumn(1).setCellRenderer(leftRenderer);
        productTableColumnModel.getColumn(2).setCellRenderer(centerRenderer);
        productTableColumnModel.getColumn(3).setCellRenderer(centerRenderer);

        productTableModel = (DefaultTableModel) productTable.getModel();
        productTable.removeColumn(productTableColumnModel.getColumn(0));

        Customer customer = (Customer) OODMS.currentUser;
        List<CartItem> cartItemList = customer.getCart();
        cartItemList.forEach(cartItem -> {
            Item item = cartItem.getItem();
            productTableModel.addRow(new Object[] {item.getId(), item.getName(), item.getPrice(), cartItem.getQuantity()});
        });
        totalPriceLb.setText(String.valueOf(CartItem.calculateTotalPrice(cartItemList)));
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
        backBtt = new javax.swing.JButton();
        JScrollPane2 = new javax.swing.JScrollPane();
        productTable = new javax.swing.JTable();
        checkOutBtt = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        unitNoField = new javax.swing.JTextField();
        unitNoLb = new javax.swing.JLabel();
        streetLb = new javax.swing.JLabel();
        streetField = new javax.swing.JTextField();
        cityLb = new javax.swing.JLabel();
        cityField = new javax.swing.JTextField();
        postalCodeLb = new javax.swing.JLabel();
        stateField = new javax.swing.JTextField();
        stateLb = new javax.swing.JLabel();
        postalCodeField = new com.mycompany.oodms.Component.JNumberField();
        paymentLb = new javax.swing.JLabel();
        paymentCb = new javax.swing.JComboBox<>();
        totalPriceLb = new javax.swing.JLabel();
        totalLb = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(1000, 550));
        setPreferredSize(new java.awt.Dimension(1000, 550));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titleLb.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        titleLb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLb.setText("Check Out");
        add(titleLb, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, -1, -1));

        backBtt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        backBtt.setText("Back");
        backBtt.setFocusable(false);
        backBtt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        backBtt.setInheritsPopupMenu(true);
        backBtt.setMargin(new java.awt.Insets(0, 0, 0, 0));
        backBtt.setPreferredSize(new java.awt.Dimension(200, 50));
        backBtt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBttActionPerformed(evt);
            }
        });
        add(backBtt, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 80, 40));

        productTable.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        productTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Price", "Quantity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        productTable.setRowHeight(25);
        productTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        productTable.getTableHeader().setReorderingAllowed(false);
        JScrollPane2.setViewportView(productTable);

        add(JScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 80, 450, 400));

        checkOutBtt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        checkOutBtt.setText("Confirm Order");
        checkOutBtt.setFocusable(false);
        checkOutBtt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkOutBttActionPerformed(evt);
            }
        });
        add(checkOutBtt, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 490, 170, 40));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        unitNoField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        unitNoField.setToolTipText("");
        unitNoField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 153, 255), 2, true));
        unitNoField.setMargin(new java.awt.Insets(2, 10, 2, 10));
        jPanel1.add(unitNoField, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 380, 30));

        unitNoLb.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        unitNoLb.setText("Unit No:");
        jPanel1.add(unitNoLb, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 120, 30));

        streetLb.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        streetLb.setText("Street Name:");
        jPanel1.add(streetLb, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 120, 30));

        streetField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        streetField.setToolTipText("");
        streetField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 153, 255), 2, true));
        streetField.setMargin(new java.awt.Insets(2, 10, 2, 10));
        jPanel1.add(streetField, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 380, 30));

        cityLb.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cityLb.setText("City:");
        jPanel1.add(cityLb, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 120, 30));

        cityField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cityField.setToolTipText("");
        cityField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 153, 255), 2, true));
        cityField.setMargin(new java.awt.Insets(2, 10, 2, 10));
        jPanel1.add(cityField, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 380, 30));

        postalCodeLb.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        postalCodeLb.setText("Postal Code:");
        jPanel1.add(postalCodeLb, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 120, 30));

        stateField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        stateField.setToolTipText("");
        stateField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 153, 255), 2, true));
        stateField.setMargin(new java.awt.Insets(2, 10, 2, 10));
        jPanel1.add(stateField, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, 380, 30));

        stateLb.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        stateLb.setText("State:");
        jPanel1.add(stateLb, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 120, 30));

        postalCodeField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 153, 255), 2, true));
        postalCodeField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        postalCodeField.setMargin(new java.awt.Insets(2, 10, 2, 10));
        jPanel1.add(postalCodeField, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 380, 30));

        paymentLb.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        paymentLb.setText("Payment Type:");
        jPanel1.add(paymentLb, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 120, 30));

        paymentCb.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        paymentCb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Debit Card", "Credit Card", "Shopee E-Wallet", "Touch 'n Go E-Wallet" }));
        jPanel1.add(paymentCb, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 320, 250, 40));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 520, 400));

        totalPriceLb.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        totalPriceLb.setText("999.99");
        add(totalPriceLb, new org.netbeans.lib.awtextra.AbsoluteConstraints(905, 490, 90, 40));

        totalLb.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        totalLb.setText("Total Price: RM");
        add(totalLb, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 490, 130, 40));
    }// </editor-fold>//GEN-END:initComponents

    private void backBttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBttActionPerformed
        OODMS.frame.refresh(new CustomerCartPage());
    }//GEN-LAST:event_backBttActionPerformed

    private void checkOutBttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkOutBttActionPerformed
        int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to confirm your order", "Warning", JOptionPane.OK_CANCEL_OPTION);
        if (option != JOptionPane.OK_OPTION) {
            return;
        }
        String address = unitNoField.getText() + ", " + streetField.getText() + ", " + cityField.getText() + " " + postalCodeField.getText() + ", " + stateField.getText();

        Customer customer = (Customer) OODMS.currentUser;
        if (!customer.checkOut((String) paymentCb.getSelectedItem(), address)) {
            OODMS.showErrorMessage();
            return;
        }
        customer.clearCartItem();
        checkOutBtt.setEnabled(false);
        JOptionPane.showMessageDialog(null, "Your order has been created, please check your order history to tract your order", "Success", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_checkOutBttActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JScrollPane2;
    private javax.swing.JButton backBtt;
    private javax.swing.JButton checkOutBtt;
    private javax.swing.JTextField cityField;
    private javax.swing.JLabel cityLb;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox<String> paymentCb;
    private javax.swing.JLabel paymentLb;
    private com.mycompany.oodms.Component.JNumberField postalCodeField;
    private javax.swing.JLabel postalCodeLb;
    private javax.swing.JTable productTable;
    private javax.swing.JTextField stateField;
    private javax.swing.JLabel stateLb;
    private javax.swing.JTextField streetField;
    private javax.swing.JLabel streetLb;
    private javax.swing.JLabel titleLb;
    private javax.swing.JLabel totalLb;
    private javax.swing.JLabel totalPriceLb;
    private javax.swing.JTextField unitNoField;
    private javax.swing.JLabel unitNoLb;
    // End of variables declaration//GEN-END:variables
}
