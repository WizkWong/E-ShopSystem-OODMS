package com.mycompany.oodms.deliveryStaff.GUI;

import com.mycompany.oodms.OODMS;
import com.mycompany.oodms.deliveryStaff.DeliveryStaffDao;
import com.mycompany.oodms.order.CustomerOrder;
import com.mycompany.oodms.order.DeliveryOrder;
import com.mycompany.oodms.order.DeliveryOrderDao;
import com.mycompany.oodms.order.DeliveryStatus;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class ViewDeliveryOrderDetailPage extends javax.swing.JPanel {

    private final DeliveryOrderDao deliveryOrderDao;
    private final DeliveryStaffDao deliveryStaffDao;
    private final CustomerOrder Order;
    private final String feedbackhtml = "<html><p style=\"width:215px;padding:2px 6px;\">%s</p></html>";
    
    public ViewDeliveryOrderDetailPage(CustomerOrder customerOrder) {
        deliveryOrderDao = OODMS.getDeliveryOrderDao();
        deliveryStaffDao = OODMS.getDeliveryStaffDao();
        Order = customerOrder;
        initComponents();
        
        if (!Order.getDeliveryOrder().getDeliveryStatus().getStatus().equalsIgnoreCase(DeliveryStatus.PENDING.toString())) {
            AcceptOrderBut.setEnabled(false);
        }
        
        // Set text for every field
        CustomerNameTxt.setText(Order.getCustomer().getUsername());
        ContactNumberTxt.setText(Order.getCustomer().getPhoneNo());
        TotalPriceTxt.setText("RM " + Order.getCustomerOrderPayment().getTotalPrice().toString());
        TotalProductTypeTxt.setText(String.valueOf(Order.getOrderDetail().size()));
        OrderStatusTxt.setText(Order.getDeliveryOrder().getDeliveryStatus().getStatus());
        OrderDateTimeTxt.setText(Order.getStringOrderDateTime());
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BackBut = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        TotalProductTypeLab = new javax.swing.JLabel();
        TotalPriceLab = new javax.swing.JLabel();
        OrderStatusLab = new javax.swing.JLabel();
        OrderDateTimeLab = new javax.swing.JLabel();
        TotalProductTypeTxt = new javax.swing.JLabel();
        TotalPriceTxt = new javax.swing.JLabel();
        OrderStatusTxt = new javax.swing.JLabel();
        OrderDateTimeTxt = new javax.swing.JLabel();
        AddressLab = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        AddressTxt = new javax.swing.JTextArea();
        CustomerNameLab = new javax.swing.JLabel();
        CustomerNameTxt = new javax.swing.JLabel();
        DeliveredDateTimeLab = new javax.swing.JLabel();
        AssignedDateTimeLab = new javax.swing.JLabel();
        AssignedDateTimeTxt = new javax.swing.JLabel();
        DeliveredDateTimeTxt = new javax.swing.JLabel();
        ContactNumberLab = new javax.swing.JLabel();
        ContactNumberTxt = new javax.swing.JLabel();
        FeedbackLab = new javax.swing.JLabel();
        FeedbackTxt = new javax.swing.JLabel();
        TitleLab = new javax.swing.JLabel();
        ProductOrderLab = new javax.swing.JLabel();
        JScrollPane2 = new javax.swing.JScrollPane();
        ProductTab = new javax.swing.JTable();
        AcceptOrderBut = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(705, 715));
        setPreferredSize(new java.awt.Dimension(705, 715));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BackBut.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BackBut.setText("Back");
        BackBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButActionPerformed(evt);
            }
        });
        add(BackBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TotalProductTypeLab.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        TotalProductTypeLab.setText("Total Product Type:");
        jPanel1.add(TotalProductTypeLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 170, 30));

        TotalPriceLab.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        TotalPriceLab.setText("Total Price:");
        jPanel1.add(TotalPriceLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 170, 30));

        OrderStatusLab.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        OrderStatusLab.setText("Order Status:");
        jPanel1.add(OrderStatusLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 170, 30));

        OrderDateTimeLab.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        OrderDateTimeLab.setText("Order Date Time:");
        jPanel1.add(OrderDateTimeLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 170, 30));

        TotalProductTypeTxt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        TotalProductTypeTxt.setText("0");
        jPanel1.add(TotalProductTypeTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 180, 30));

        TotalPriceTxt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        TotalPriceTxt.setText("RM 00.00");
        jPanel1.add(TotalPriceTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 180, 30));

        OrderStatusTxt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        OrderStatusTxt.setText("Pending");
        jPanel1.add(OrderStatusTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 180, 30));

        OrderDateTimeTxt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        OrderDateTimeTxt.setText("00-00-0000");
        jPanel1.add(OrderDateTimeTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, 180, 30));

        AddressLab.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        AddressLab.setText("Address:");
        jPanel1.add(AddressLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 80, 30));

        AddressTxt.setColumns(20);
        AddressTxt.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        AddressTxt.setRows(5);
        AddressTxt.setFocusable(false);
        AddressTxt.setMargin(new java.awt.Insets(2, 8, 2, 8));
        AddressTxt.setVerifyInputWhenFocusTarget(false);
        jScrollPane2.setViewportView(AddressTxt);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, 260, 120));

        CustomerNameLab.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        CustomerNameLab.setText("Customer Name:");
        jPanel1.add(CustomerNameLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 170, 30));

        CustomerNameTxt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        CustomerNameTxt.setText("Name");
        jPanel1.add(CustomerNameTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 420, 30));

        DeliveredDateTimeLab.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        DeliveredDateTimeLab.setText("Delivered Date Time:");
        jPanel1.add(DeliveredDateTimeLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 170, 30));

        AssignedDateTimeLab.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        AssignedDateTimeLab.setText("Assigned Date Time:");
        jPanel1.add(AssignedDateTimeLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 170, 30));

        AssignedDateTimeTxt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        AssignedDateTimeTxt.setText("00-00-0000");
        jPanel1.add(AssignedDateTimeTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, 180, 30));

        DeliveredDateTimeTxt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        DeliveredDateTimeTxt.setText("00-00-0000");
        jPanel1.add(DeliveredDateTimeTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 290, 180, 30));

        ContactNumberLab.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ContactNumberLab.setText("Contact Number:");
        jPanel1.add(ContactNumberLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 170, 30));

        ContactNumberTxt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ContactNumberTxt.setText("000 000 0000");
        jPanel1.add(ContactNumberTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 180, 30));

        FeedbackLab.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        FeedbackLab.setText("Feedback:");
        jPanel1.add(FeedbackLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 170, 90, 30));

        FeedbackTxt.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        FeedbackTxt.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        FeedbackTxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanel1.add(FeedbackTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 200, 260, 120));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 650, 330));

        TitleLab.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        TitleLab.setText("View Delivery Order Detail");
        add(TitleLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, -1, -1));

        ProductOrderLab.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        ProductOrderLab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ProductOrderLab.setText("Product Order");
        add(ProductOrderLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 420, -1, 30));

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

        add(JScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 460, 630, 190));

        AcceptOrderBut.setFont(new java.awt.Font("Calibri", 2, 18)); // NOI18N
        AcceptOrderBut.setText("Accept Order");
        AcceptOrderBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AcceptOrderButActionPerformed(evt);
            }
        });
        add(AcceptOrderBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 660, 170, 40));
    }// </editor-fold>//GEN-END:initComponents

    private void BackButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButActionPerformed
        OODMS.frame.refresh(new ViewDeliveryOrderPage());
    }//GEN-LAST:event_BackButActionPerformed

    private void AcceptOrderButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AcceptOrderButActionPerformed
        // Confirmation on accepting order
        int option = JOptionPane.showConfirmDialog(null, "You must deliver the order after accepting.\nConfirm on accepting order?", "Accept Order", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (option != JOptionPane.OK_OPTION) {
            return;
        }
        
        // Set delivery order details
        DeliveryOrder deliveryOrder = Order.getDeliveryOrder();
        deliveryOrder.setDeliveryStatus(DeliveryStatus.DELIVERING);
        
        // Update text file with the newly modifed object
        if (deliveryOrderDao.fileUpdate(deliveryOrder)) {
            JOptionPane.showMessageDialog(null, "Successfully accepted order", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Failed to accept order", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
        
        OODMS.frame.refresh(new ViewDeliveryOrderDetailPage(Order));
    }//GEN-LAST:event_AcceptOrderButActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AcceptOrderBut;
    private javax.swing.JLabel AddressLab;
    private javax.swing.JTextArea AddressTxt;
    private javax.swing.JLabel AssignedDateTimeLab;
    private javax.swing.JLabel AssignedDateTimeTxt;
    private javax.swing.JButton BackBut;
    private javax.swing.JLabel ContactNumberLab;
    private javax.swing.JLabel ContactNumberTxt;
    private javax.swing.JLabel CustomerNameLab;
    private javax.swing.JLabel CustomerNameTxt;
    private javax.swing.JLabel DeliveredDateTimeLab;
    private javax.swing.JLabel DeliveredDateTimeTxt;
    private javax.swing.JLabel FeedbackLab;
    private javax.swing.JLabel FeedbackTxt;
    private javax.swing.JScrollPane JScrollPane2;
    private javax.swing.JLabel OrderDateTimeLab;
    private javax.swing.JLabel OrderDateTimeTxt;
    private javax.swing.JLabel OrderStatusLab;
    private javax.swing.JLabel OrderStatusTxt;
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