package com.mycompany.oodms.admin.GUI;

import com.mycompany.oodms.OODMS;
import com.mycompany.oodms.deliveryStaff.DeliveryStaff;
import com.mycompany.oodms.deliveryStaff.DeliveryStaffDao;
import com.mycompany.oodms.order.CustomerOrder;
import com.mycompany.oodms.order.DeliveryOrder;
import com.mycompany.oodms.order.DeliveryOrderDao;
import com.mycompany.oodms.order.DeliveryStatus;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class ViewOrderPage extends javax.swing.JPanel {   
    
    private final DeliveryOrderDao deliveryOrderDao;
    private final DeliveryStaffDao deliveryStaffDao;
    private final CustomerOrder Order;
    private final String feedbackhtml = "<html><p style=\"width:215px;padding:2px 6px;\">%s</p></html>";
    
    public ViewOrderPage(CustomerOrder customerOrder) {
        deliveryOrderDao = OODMS.getDeliveryOrderDao();
        deliveryStaffDao = OODMS.getDeliveryStaffDao();
        Order = customerOrder;
        initComponents();        
        
        if (Order.getDeliveryOrder().getDeliveryStatus().getStatus().equalsIgnoreCase(DeliveryStatus.DELIVERING.toString()) 
                || Order.getDeliveryOrder().getDeliveryStatus().getStatus().equalsIgnoreCase(DeliveryStatus.DELIVERED.toString())) {
            AssignOrderBut.setEnabled(false);
        }
        
        // Set text for every field
        CustomerNameTxt.setText(Order.getCustomer().getUsername());
        ContactNumberTxt.setText(Order.getCustomer().getPhoneNo());
        PaymentTypeTxt.setText(Order.getCustomerOrderPayment().getTypeOfPayment());
        TotalPriceTxt.setText("RM " + Order.getCustomerOrderPayment().getTotalPrice().toString());
        TotalProductTypeTxt.setText(String.valueOf(Order.getOrderDetail().size()));
        OrderStatusTxt.setText(Order.getDeliveryOrder().getDeliveryStatus().getStatus());
        OrderDateTimeTxt.setText(Order.getStringOrderDateTime());        
        DeliveryStaffNameTxt.setText(Order.getDeliveryOrder().getDeliveryStaff()==null?"N/A":Order.getDeliveryOrder().getDeliveryStaff().getUsername());
        AssignedDateTimeTxt.setText(Order.getDeliveryOrder().getStringAssignDateTime()==null?"N/A":Order.getDeliveryOrder().getStringAssignDateTime());
        DeliveredDateTimeTxt.setText(Order.getDeliveryOrder().getStringDeliveredDateTime()==null?"N/A":Order.getDeliveryOrder().getStringDeliveredDateTime());
        AddressTxt.setText(Order.getDeliveryOrder().getAddress().replace(",", ",\n"));
        FeedbackTxt.setText(Order.getDeliveryOrder().getFeedback()==null?String.format(feedbackhtml, "No feedback yet"):String.format(feedbackhtml, Order.getDeliveryOrder().getFeedback()));
        
        // Center the allignment of the table
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        TableColumnModel ProductTableColumnModel = ProductTab.getColumnModel();
        ProductTableColumnModel.getColumn(0).setCellRenderer(centerRenderer);
        ProductTableColumnModel.getColumn(1).setCellRenderer(centerRenderer);
        ProductTableColumnModel.getColumn(2).setCellRenderer(centerRenderer);
        
        //Not allowing focus selection on single cell
        ProductTab.setFocusable(false);

        // Append data into the product table
        DefaultTableModel productTableModel = (DefaultTableModel) ProductTab.getModel();
        customerOrder.getOrderDetail().forEach(orderD -> productTableModel.addRow(new Object[] {orderD.getItem().getName(), orderD.getOrderPrice(), orderD.getQuantity()}));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BackBut = new javax.swing.JButton();
        TitleLab = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        PaymentTypeLab = new javax.swing.JLabel();
        TotalProductTypeLab = new javax.swing.JLabel();
        TotalPriceLab = new javax.swing.JLabel();
        OrderStatusLab = new javax.swing.JLabel();
        OrderDateTimeLab = new javax.swing.JLabel();
        PaymentTypeTxt = new javax.swing.JLabel();
        TotalProductTypeTxt = new javax.swing.JLabel();
        TotalPriceTxt = new javax.swing.JLabel();
        OrderStatusTxt = new javax.swing.JLabel();
        OrderDateTimeTxt = new javax.swing.JLabel();
        AddressLab = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        AddressTxt = new javax.swing.JTextArea();
        CustomerNameLab = new javax.swing.JLabel();
        CustomerNameTxt = new javax.swing.JLabel();
        DeliveryStaffNameLab = new javax.swing.JLabel();
        DeliveredDateTimeLab = new javax.swing.JLabel();
        AssignedDateTimeLab = new javax.swing.JLabel();
        DeliveryStaffNameTxt = new javax.swing.JLabel();
        AssignedDateTimeTxt = new javax.swing.JLabel();
        DeliveredDateTimeTxt = new javax.swing.JLabel();
        ContactNumberLab = new javax.swing.JLabel();
        ContactNumberTxt = new javax.swing.JLabel();
        FeedbackLab = new javax.swing.JLabel();
        FeedbackTxt = new javax.swing.JLabel();
        JScrollPane2 = new javax.swing.JScrollPane();
        ProductTab = new javax.swing.JTable();
        ProductOrderLab = new javax.swing.JLabel();
        AssignOrderBut = new javax.swing.JButton();
        AssignOrderLab = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(705, 800));
        setPreferredSize(new java.awt.Dimension(705, 800));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BackBut.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BackBut.setText("Back");
        BackBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButActionPerformed(evt);
            }
        });
        add(BackBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        TitleLab.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        TitleLab.setText("View Order");
        add(TitleLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PaymentTypeLab.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        PaymentTypeLab.setText("Payment Type:");
        jPanel1.add(PaymentTypeLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 170, 30));

        TotalProductTypeLab.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        TotalProductTypeLab.setText("Total Product Type:");
        jPanel1.add(TotalProductTypeLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 170, 30));

        TotalPriceLab.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        TotalPriceLab.setText("Total Price:");
        jPanel1.add(TotalPriceLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 170, 30));

        OrderStatusLab.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        OrderStatusLab.setText("Order Status:");
        jPanel1.add(OrderStatusLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 170, 30));

        OrderDateTimeLab.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        OrderDateTimeLab.setText("Order Date Time:");
        jPanel1.add(OrderDateTimeLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 170, 30));

        PaymentTypeTxt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        PaymentTypeTxt.setText("Type");
        jPanel1.add(PaymentTypeTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 180, 30));

        TotalProductTypeTxt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        TotalProductTypeTxt.setText("0");
        jPanel1.add(TotalProductTypeTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 180, 30));

        TotalPriceTxt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        TotalPriceTxt.setText("RM 00.00");
        jPanel1.add(TotalPriceTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 180, 30));

        OrderStatusTxt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        OrderStatusTxt.setText("Pending");
        jPanel1.add(OrderStatusTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, 180, 30));

        OrderDateTimeTxt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        OrderDateTimeTxt.setText("00-00-0000");
        jPanel1.add(OrderDateTimeTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, 180, 30));

        AddressLab.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        AddressLab.setText("Address:");
        jPanel1.add(AddressLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, 80, 30));

        AddressTxt.setColumns(20);
        AddressTxt.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        AddressTxt.setRows(5);
        AddressTxt.setFocusable(false);
        AddressTxt.setMargin(new java.awt.Insets(2, 8, 2, 8));
        AddressTxt.setVerifyInputWhenFocusTarget(false);
        jScrollPane2.setViewportView(AddressTxt);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, 260, 130));

        CustomerNameLab.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        CustomerNameLab.setText("Customer Name:");
        jPanel1.add(CustomerNameLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 170, 30));

        CustomerNameTxt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        CustomerNameTxt.setText("Name");
        jPanel1.add(CustomerNameTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 420, 30));

        DeliveryStaffNameLab.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        DeliveryStaffNameLab.setText("Delivery Staff Name:");
        jPanel1.add(DeliveryStaffNameLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 170, 30));

        DeliveredDateTimeLab.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        DeliveredDateTimeLab.setText("Delivered Date Time:");
        jPanel1.add(DeliveredDateTimeLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 170, 30));

        AssignedDateTimeLab.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        AssignedDateTimeLab.setText("Assigned Date Time:");
        jPanel1.add(AssignedDateTimeLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 170, 30));

        DeliveryStaffNameTxt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        DeliveryStaffNameTxt.setText("Name");
        jPanel1.add(DeliveryStaffNameTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 290, 180, 30));

        AssignedDateTimeTxt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        AssignedDateTimeTxt.setText("00-00-0000");
        jPanel1.add(AssignedDateTimeTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 330, 180, 30));

        DeliveredDateTimeTxt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        DeliveredDateTimeTxt.setText("00-00-0000");
        jPanel1.add(DeliveredDateTimeTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 370, 180, 30));

        ContactNumberLab.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ContactNumberLab.setText("Contact Number:");
        jPanel1.add(ContactNumberLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 170, 30));

        ContactNumberTxt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ContactNumberTxt.setText("000 000 0000");
        jPanel1.add(ContactNumberTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 180, 30));

        FeedbackLab.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        FeedbackLab.setText("Feedback:");
        jPanel1.add(FeedbackLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 250, 90, 30));

        FeedbackTxt.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        FeedbackTxt.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        FeedbackTxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanel1.add(FeedbackTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 280, 260, 120));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 650, 410));

        ProductTab.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        ProductTab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Price", "Quantity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ProductTab.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        ProductTab.setRowHeight(25);
        ProductTab.setRowSelectionAllowed(false);
        ProductTab.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ProductTab.getTableHeader().setReorderingAllowed(false);
        JScrollPane2.setViewportView(ProductTab);
        if (ProductTab.getColumnModel().getColumnCount() > 0) {
            ProductTab.getColumnModel().getColumn(0).setPreferredWidth(200);
            ProductTab.getColumnModel().getColumn(1).setPreferredWidth(70);
            ProductTab.getColumnModel().getColumn(2).setPreferredWidth(60);
        }

        add(JScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 530, 630, 190));

        ProductOrderLab.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        ProductOrderLab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ProductOrderLab.setText("Product Order");
        add(ProductOrderLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 490, -1, 30));

        AssignOrderBut.setFont(new java.awt.Font("Calibri", 2, 18)); // NOI18N
        AssignOrderBut.setText("Assign Order");
        AssignOrderBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AssignOrderButActionPerformed(evt);
            }
        });
        add(AssignOrderBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 730, 170, 40));

        AssignOrderLab.setForeground(new java.awt.Color(204, 0, 255));
        AssignOrderLab.setText("*Assign Order is only available when order status is unassigned or pending");
        add(AssignOrderLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 770, 400, 20));
    }// </editor-fold>//GEN-END:initComponents

    private void BackButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButActionPerformed
        OODMS.frame.refresh(new OrderDeliveryManagementPage());
    }//GEN-LAST:event_BackButActionPerformed

    private void AssignOrderButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AssignOrderButActionPerformed
        // Get every delivery stuff data using deliveryStaffDao.getAll method
        List<DeliveryStaff> allDeliveryStaff = deliveryStaffDao.getAll();
        
        // Make an array with the size of allDeliveryStaff list
        String DeliveryStaffArray[] = new String[allDeliveryStaff.size()];
        
        
        Boolean NullValue = false;
        // Append every delivery stuff username except the current assigned delivery staff into the DeliveryStaffArray
        for (int i = 0; i < allDeliveryStaff.size(); i++) {
            if (!allDeliveryStaff.get(i).getUsername().equals(DeliveryStaffNameTxt.getText())) {
                DeliveryStaffArray[i] = allDeliveryStaff.get(i).getUsername();
            } else {
                NullValue = true;
            }
        }

        // Show input dialog with the delivery stuff username as option
        String choice = null;
        // If there is null value in DeliveryStaffArray, it will be remove and change to NewDeliveryStaffArray
        if (NullValue) {
            String NewDeliveryStaffArray[] = new String[DeliveryStaffArray.length - 1];
            int i1 = 0;
            for (int i2 = 0; i2 < DeliveryStaffArray.length; i2++) {
                if (DeliveryStaffArray[i2] == null) {   
                } else {
                    System.out.print(DeliveryStaffArray[i2]);
                    NewDeliveryStaffArray[i1] = DeliveryStaffArray[i2];
                    i1 += 1;
                }
            }

            choice = (String)JOptionPane.showInputDialog(null, "Which delivery staff do you want to change to?", 
            "Assign Order", JOptionPane.QUESTION_MESSAGE, null, NewDeliveryStaffArray, null);
        } else {
            choice = (String)JOptionPane.showInputDialog(null, "Which delivery staff do you want to assign to?", 
            "Assign Order", JOptionPane.QUESTION_MESSAGE, null, DeliveryStaffArray, null);
        }
                
        // If cancel button is press
        if (choice == null) {
            return;
        }
        
        // Get delivery staff object by using the username from the choice
        DeliveryStaff deliveryStaff = null;
        for (int i = 0; i < allDeliveryStaff.size(); i++) {
            if (allDeliveryStaff.get(i).getUsername().equals(choice)) {
                deliveryStaff = allDeliveryStaff.get(i);
            }
        }
        
        // Set delivery order details
        DeliveryOrder deliveryOrder = Order.getDeliveryOrder();
        deliveryOrder.setDeliveryStatus(DeliveryStatus.PENDING);
        deliveryOrder.setDeliveryStaff(deliveryStaff);
        deliveryOrder.setAssignDateTime(LocalDateTime.now());
        
        // Modify text file with the newly modifed object
        if (deliveryOrderDao.fileUpdate(deliveryOrder)) {
            JOptionPane.showMessageDialog(null, "Successfully assigned order to " +  deliveryStaff.getUsername(), "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Failed to assign order", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
        
        OODMS.frame.refresh(new ViewOrderPage(Order));
    }//GEN-LAST:event_AssignOrderButActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AddressLab;
    private javax.swing.JTextArea AddressTxt;
    private javax.swing.JButton AssignOrderBut;
    private javax.swing.JLabel AssignOrderLab;
    private javax.swing.JLabel AssignedDateTimeLab;
    private javax.swing.JLabel AssignedDateTimeTxt;
    private javax.swing.JButton BackBut;
    private javax.swing.JLabel ContactNumberLab;
    private javax.swing.JLabel ContactNumberTxt;
    private javax.swing.JLabel CustomerNameLab;
    private javax.swing.JLabel CustomerNameTxt;
    private javax.swing.JLabel DeliveredDateTimeLab;
    private javax.swing.JLabel DeliveredDateTimeTxt;
    private javax.swing.JLabel DeliveryStaffNameLab;
    private javax.swing.JLabel DeliveryStaffNameTxt;
    private javax.swing.JLabel FeedbackLab;
    private javax.swing.JLabel FeedbackTxt;
    private javax.swing.JScrollPane JScrollPane2;
    private javax.swing.JLabel OrderDateTimeLab;
    private javax.swing.JLabel OrderDateTimeTxt;
    private javax.swing.JLabel OrderStatusLab;
    private javax.swing.JLabel OrderStatusTxt;
    private javax.swing.JLabel PaymentTypeLab;
    private javax.swing.JLabel PaymentTypeTxt;
    private javax.swing.JLabel ProductOrderLab;
    private javax.swing.JTable ProductTab;
    private javax.swing.JLabel TitleLab;
    private javax.swing.JLabel TotalPriceLab;
    private javax.swing.JLabel TotalPriceTxt;
    private javax.swing.JLabel TotalProductTypeLab;
    private javax.swing.JLabel TotalProductTypeTxt;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
