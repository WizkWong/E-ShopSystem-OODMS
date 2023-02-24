package com.mycompany.oodms.admin.GUI;

import com.mycompany.oodms.Component.CreateAccountForm;
import com.mycompany.oodms.Component.EditAccountForm;
import com.mycompany.oodms.OODMS;
import com.mycompany.oodms.admin.Admin;
import com.mycompany.oodms.admin.AdminDao;
import com.mycompany.oodms.customer.CustomerDao;
import com.mycompany.oodms.deliveryStaff.DeliveryStaff;
import com.mycompany.oodms.deliveryStaff.DeliveryStaffDao;
import com.mycompany.oodms.user.User;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;
import java.util.List;

public class AccountManagementPage extends javax.swing.JPanel {
    
    private final AdminDao adminDao;
    private final DeliveryStaffDao deliveryStaffDao;
    private final CustomerDao customerDao;
    private final DefaultTableModel accountTableModel;
    
    private static boolean adminSection = true;
    private static boolean deliveryStaffSection = false;
    private static boolean customerSection = false;
    /**
     * Creates new form AccountManagementPage
     */
    public AccountManagementPage() {
        adminDao = OODMS.getAdminDao();
        deliveryStaffDao = OODMS.getDeliveryStaffDao();
        customerDao = OODMS.getCustomerDao();
        initComponents();

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        // align the table column to center
        accountTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

        accountTableModel = (DefaultTableModel) accountTable.getModel();

        // if selected the delivery staff in previous, auto select it back
        if (deliveryStaffSection) {
            adminBtt.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            dStaffBtt.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            customerBtt.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            // get all delivery staff and load into GUI table
            loadTable(deliveryStaffDao.getAll().stream().map(deliveryStaff -> (User) deliveryStaff).toList());
        } else if (customerSection) {
            adminBtt.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            dStaffBtt.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            customerBtt.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            // get all customer and load into GUI table
            loadTable(customerDao.getAll().stream().map(customer -> (User) customer).toList());
        } else {
            loadTable(adminDao.getAll().stream().map(admin -> (User) admin).toList());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        accountTable = new javax.swing.JTable();
        dStaffBtt = new javax.swing.JButton();
        adminBtt = new javax.swing.JButton();
        customerBtt = new javax.swing.JButton();
        backBtt = new javax.swing.JButton();
        editBtt = new javax.swing.JButton();
        removeBtt = new javax.swing.JButton();
        createBtt = new javax.swing.JButton();
        searchFd = new javax.swing.JTextField();
        searchLb = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(1000, 600));
        setPreferredSize(new java.awt.Dimension(1000, 600));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        accountTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Username", "Email", "Phone Number"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        accountTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                accountTableMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(accountTable);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 690, -1));

        dStaffBtt.setFont(new java.awt.Font("Calibri", 2, 18)); // NOI18N
        dStaffBtt.setText("Delivery Staff");
        dStaffBtt.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dStaffBtt.setFocusable(false);
        dStaffBtt.setMargin(new java.awt.Insets(3, 14, 3, 14));
        dStaffBtt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dStaffBttActionPerformed(evt);
            }
        });
        add(dStaffBtt, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 140, -1));

        adminBtt.setFont(new java.awt.Font("Calibri", 2, 18)); // NOI18N
        adminBtt.setText("Admin");
        adminBtt.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        adminBtt.setFocusable(false);
        adminBtt.setMargin(new java.awt.Insets(3, 14, 3, 14));
        adminBtt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminBttActionPerformed(evt);
            }
        });
        add(adminBtt, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 140, -1));

        customerBtt.setFont(new java.awt.Font("Calibri", 2, 18)); // NOI18N
        customerBtt.setText("Customer");
        customerBtt.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        customerBtt.setFocusable(false);
        customerBtt.setMargin(new java.awt.Insets(3, 14, 3, 14));
        customerBtt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerBttActionPerformed(evt);
            }
        });
        add(customerBtt, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 100, 140, -1));

        backBtt.setFont(new java.awt.Font("Calibri", 2, 18)); // NOI18N
        backBtt.setText("Back");
        backBtt.setFocusable(false);
        backBtt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBttActionPerformed(evt);
            }
        });
        add(backBtt, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        editBtt.setFont(new java.awt.Font("Calibri", 2, 18)); // NOI18N
        editBtt.setText("Edit Account");
        editBtt.setEnabled(false);
        editBtt.setFocusable(false);
        editBtt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBttActionPerformed(evt);
            }
        });
        add(editBtt, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 200, 150, -1));

        removeBtt.setFont(new java.awt.Font("Calibri", 2, 18)); // NOI18N
        removeBtt.setText("Remove Account");
        removeBtt.setEnabled(false);
        removeBtt.setFocusable(false);
        removeBtt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeBttActionPerformed(evt);
            }
        });
        add(removeBtt, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 270, -1, -1));

        createBtt.setFont(new java.awt.Font("Calibri", 2, 18)); // NOI18N
        createBtt.setText("Create Account");
        createBtt.setToolTipText("");
        createBtt.setFocusable(false);
        createBtt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createBttActionPerformed(evt);
            }
        });
        add(createBtt, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 130, 150, -1));

        searchFd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        searchFd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchEngine(evt);
            }
        });
        add(searchFd, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 580, -1));

        searchLb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        searchLb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        searchLb.setText("Search Account:");
        add(searchLb, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 120, 40));
    }// </editor-fold>//GEN-END:initComponents

    // load all user information into GUI table
    private void loadTable(List<User> userList) {
        int itemRow = accountTableModel.getRowCount();
        // remove all the rows in GUI table
        for (int i = itemRow - 1; i >= 0 ; i--) {
            accountTableModel.removeRow(i);
        }
        // load all the userList into table
        userList.forEach(user -> accountTableModel.addRow(new Object[] {user.getId(), user.getUsername(), user.getEmail(), user.getPhoneNo()}));
        // set the button to disabled
        editBtt.setEnabled(false);
        removeBtt.setEnabled(false);
    }

    // switch to show delivery staff
    private void dStaffBttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dStaffBttActionPerformed
        if (deliveryStaffSection) {
            return;
        }
        createBtt.setEnabled(true);
        adminSection = false;
        deliveryStaffSection = true;
        customerSection = false;
        adminBtt.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        dStaffBtt.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        customerBtt.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        // get all delivery staff and load into GUI table
        loadTable(deliveryStaffDao.getAll().stream().map(deliveryStaff -> (User) deliveryStaff).toList());
    }//GEN-LAST:event_dStaffBttActionPerformed

    // switch to show admin
    private void adminBttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminBttActionPerformed
        if (adminSection) {
            return;
        }
        createBtt.setEnabled(true);
        adminSection = true;
        deliveryStaffSection = false;
        customerSection = false;
        adminBtt.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        dStaffBtt.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        customerBtt.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        // get all admin and load into GUI table
        loadTable(adminDao.getAll().stream().map(admin -> (User) admin).toList());
    }//GEN-LAST:event_adminBttActionPerformed

    private void backBttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBttActionPerformed
        OODMS.frame.refresh(new AdminPanelForm());
    }//GEN-LAST:event_backBttActionPerformed

    // check user exist
    private User checkUserExist() {
        int select = accountTable.getSelectedRow();
        long id = (long) accountTableModel.getValueAt(select, 0);
        User user = null;

        if (adminSection) {
            // get admin
            user = adminDao.getById(id);
        } else if (deliveryStaffSection) {
            // get delivery staff
            user = deliveryStaffDao.getById(id);
        }
        return user;
    }

    // edit the user
    private void editBttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBttActionPerformed
        User user = checkUserExist();
        // if user does not exist, prompt error message
        if (user == null) {
            JOptionPane.showMessageDialog(null, "User does not exist, please refresh the page", "User does not Exist", JOptionPane.ERROR_MESSAGE);
            return;
        }
        EditAccountForm form = new EditAccountForm(user);
        while (true) {
            // prompt a GUI form
            int option = JOptionPane.showConfirmDialog(null, form, "Edit Account", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (option != JOptionPane.OK_OPTION) {
                break;
            }
            // validate the input and update user profile
            if (form.formValidate()) {
                JOptionPane.showMessageDialog(null, "Successfully Update Account", "Success", JOptionPane.INFORMATION_MESSAGE);
                OODMS.frame.refresh(new AccountManagementPage());
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update account", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_editBttActionPerformed

    // remove the user
    private void removeBttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeBttActionPerformed
        User user = checkUserExist();
        // if user does not exist, prompt error message
        if (user == null) {
            JOptionPane.showMessageDialog(null, "User does not exist, please refresh the page", "User does not Exist", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // if only one admin account left, cannot allow the user to delete the last admin account
        if (accountTableModel.getRowCount() == 1 && adminSection) {
            JOptionPane.showMessageDialog(null, "Last Admin Account cannot be deleted", "Illegal Action", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // pop-up confirm dialog-box
        int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete User: " + user.getUsername(), "Warning", JOptionPane.OK_CANCEL_OPTION);
        if (option != JOptionPane.OK_OPTION) {
            return;
        }
        boolean removed = false;
        // remove the admin or delivery staff account
        if (user instanceof Admin admin) {
            removed = adminDao.remove(admin);
        } else if (user instanceof DeliveryStaff deliveryStaff) {
            removed = deliveryStaffDao.remove(deliveryStaff);
        }
        // if remove success
        if (removed) {
            JOptionPane.showMessageDialog(null, "Successfully Removed Account", "Success", JOptionPane.INFORMATION_MESSAGE);
            OODMS.frame.refresh(new AccountManagementPage());
        } else {
            JOptionPane.showMessageDialog(null, "Failed to removed account", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_removeBttActionPerformed

    // create the user
    private void createBttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createBttActionPerformed
        CreateAccountForm form = new CreateAccountForm();
        while (true) {
            // prompt a GUI form
            int option = JOptionPane.showConfirmDialog(null, form, "Add Account", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (option != JOptionPane.OK_OPTION) {
                break;
            }
            // validate the input and create user
            if (form.formValidate()) {
                JOptionPane.showMessageDialog(null, "Successfully Created Account", "Success", JOptionPane.INFORMATION_MESSAGE);
                OODMS.frame.refresh(new AccountManagementPage());
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Failed to create account", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_createBttActionPerformed

    // search the user by user information
    private void searchEngine(KeyEvent evt) {//GEN-FIRST:event_searchEngine
        // get the search field text
        String searchTxt = searchFd.getText().toLowerCase();
        List<User> userList = List.of();
        if (adminSection) {
            // get all the admin
            userList = adminDao.getAll().stream().map(admin -> (User) admin).toList();

        } else if (deliveryStaffSection) {
            // get all the delivery staff
            userList = deliveryStaffDao.getAll().stream().map(deliveryStaff -> (User) deliveryStaff).toList();

        } else if (customerSection) {
            // get all the customer
            userList = customerDao.getAll().stream().map(customer -> (User) customer).toList();
        }
        // if search field is empty, load all user into GUI table without filter it
        if (!searchTxt.equals("")) {
            // filter the user by search text field
            userList = userList.stream().filter(user ->
                    user.getId().toString().contains(searchTxt) ||
                    user.getUsername().toLowerCase().contains(searchTxt) ||
                    user.getEmail().toLowerCase().contains(searchTxt) ||
                    user.getPhoneNo().contains(searchTxt)
            ).toList();
        }
        loadTable(userList);
    }//GEN-LAST:event_searchEngine

    private void accountTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accountTableMousePressed
        if (customerSection) {
            editBtt.setEnabled(false);
            removeBtt.setEnabled(false);
            return;
        }
        editBtt.setEnabled(true);
        removeBtt.setEnabled(true);
    }//GEN-LAST:event_accountTableMousePressed

    private void customerBttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerBttActionPerformed
        if (customerSection) {
            return;
        }
        createBtt.setEnabled(false);
        adminSection = false;
        deliveryStaffSection = false;
        customerSection = true;
        adminBtt.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        dStaffBtt.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        customerBtt.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        // get all admin and load into GUI table
        loadTable(customerDao.getAll().stream().map(customer -> (User) customer).toList());
    }//GEN-LAST:event_customerBttActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable accountTable;
    private javax.swing.JButton adminBtt;
    private javax.swing.JButton backBtt;
    private javax.swing.JButton createBtt;
    private javax.swing.JButton customerBtt;
    private javax.swing.JButton dStaffBtt;
    private javax.swing.JButton editBtt;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton removeBtt;
    private javax.swing.JTextField searchFd;
    private javax.swing.JLabel searchLb;
    // End of variables declaration//GEN-END:variables
}
