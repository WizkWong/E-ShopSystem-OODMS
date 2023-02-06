/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.oodms;

import com.mycompany.oodms.admin.Admin;
import com.mycompany.oodms.customer.CartItem;
import com.mycompany.oodms.customer.Customer;
import com.mycompany.oodms.deliveryStaff.DeliveryStaff;
import com.mycompany.oodms.item.Item;
import com.mycompany.oodms.order.CustomerOrder;
import com.mycompany.oodms.order.CustomerOrderPayment;
import com.mycompany.oodms.order.OrderDetail;
import com.mycompany.oodms.user.GUI.HomePage;
import com.mycompany.oodms.user.User;

public class OODMS {

    public static MainFrame frame;
    public static User currentUser;

    public OODMS() {
        initialize();
        frame = new MainFrame(new HomePage());
    }

    public void initialize() {
        FileService.createFile(User.USER_FILENAME);
        FileService.createFile(Customer.FILENAME);
        FileService.createFile(DeliveryStaff.FILENAME);
        FileService.createFile(Admin.FILENAME);
        FileService.createFile(Item.FILENAME);
        FileService.createFile(Item.CATEGORY_FILENAME);
        FileService.createFile(CartItem.FILENAME);
        FileService.createFile(CustomerOrder.FILENAME);
        FileService.createFile(CustomerOrderPayment.FILENAME);
        FileService.createFile(OrderDetail.FILENAME);
    }

    public static void main(String[] args) {
        new OODMS();
    }
}
