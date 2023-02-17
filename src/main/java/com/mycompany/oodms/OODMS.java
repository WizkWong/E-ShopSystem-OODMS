package com.mycompany.oodms;

import com.mycompany.oodms.Dao.FileService;
import com.mycompany.oodms.admin.AdminDao;
import com.mycompany.oodms.customer.CartItemDao;
import com.mycompany.oodms.customer.Customer;
import com.mycompany.oodms.customer.CustomerDao;
import com.mycompany.oodms.deliveryStaff.DeliveryStaffDao;
import com.mycompany.oodms.item.ItemDao;
import com.mycompany.oodms.order.*;
import com.mycompany.oodms.user.GUI.HomePage;
import com.mycompany.oodms.user.User;
import com.mycompany.oodms.user.UserDao;

import javax.swing.JOptionPane;

public class OODMS {

    public static MainFrame frame;
    public static User currentUser;

    private static final UserDao userDao = new UserDao();
    private static final CustomerDao customerDao = new CustomerDao();
    private static final DeliveryStaffDao deliveryStaffDao = new DeliveryStaffDao();
    private static final AdminDao adminDao = new AdminDao();
    private static final ItemDao itemDao = new ItemDao();
    private static final CartItemDao cartItemDao = new CartItemDao();
    private static final CustomerOrderDao customerOrderDao = new CustomerOrderDao();
    private static final CustomerOrderPaymentDao customerOrderPaymentDao = new CustomerOrderPaymentDao();
    private static final OrderDetailDao orderDetailDao = new OrderDetailDao();

    public OODMS() {
        initialize();
        frame = new MainFrame(new HomePage());
    }

    public void initialize() {
        FileService.createFile(UserDao.FILENAME);
        FileService.createFile(Customer.FILENAME);
        FileService.createFile(DeliveryStaffDao.FILENAME);
        FileService.createFile(AdminDao.FILENAME);
        FileService.createFile(ItemDao.ITEM_FILENAME);
        FileService.createFile(ItemDao.CATEGORY_FILENAME);
        FileService.createFile(CartItemDao.FILENAME);
        FileService.createFile(CustomerOrderDao.FILENAME);
        FileService.createFile(CustomerOrderPaymentDao.FILENAME);
        FileService.createFile(OrderDetailDao.FILENAME);
    }

    public static void showErrorMessage() {
        JOptionPane.showMessageDialog(null, "The system had met an error, please contact the technical support", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static UserDao getUserDao() {
        return userDao;
    }

    public static CustomerDao getCustomerDao() {
        return customerDao;
    }

    public static DeliveryStaffDao getDeliveryStaffDao() {
        return deliveryStaffDao;
    }

    public static AdminDao getAdminDao() {
        return adminDao;
    }

    public static ItemDao getItemDao() {
        return itemDao;
    }

    public static CartItemDao getCartItemDao() {
        return cartItemDao;
    }

    public static CustomerOrderDao getCustomerOrderDao() {
        return customerOrderDao;
    }

    public static CustomerOrderPaymentDao getCustomerOrderPaymentDao() {
        return customerOrderPaymentDao;
    }

    public static OrderDetailDao getOrderDetailDao() {
        return orderDetailDao;
    }

    public static void main(String[] args) {
        new OODMS();
    }
}
