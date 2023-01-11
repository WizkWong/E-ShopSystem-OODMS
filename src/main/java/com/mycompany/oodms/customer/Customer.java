package com.mycompany.oodms.customer;

import com.mycompany.oodms.FileService;
import com.mycompany.oodms.user.User;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    public static final String FILENAME = "customer";

    private String phoneNo;
    private List<CartItem> cart;

    public Customer(Long id, String username, String password, Boolean staff, Boolean admin, String phoneNo) {
        super(id, username, password, staff, admin);
        this.phoneNo = phoneNo;
        this.cart = CartItem.getCartItem(id);
    }

    public Customer(List<String> customerData) {
        this(
                Long.valueOf(customerData.get(0)),
                customerData.get(1),
                customerData.get(2),
                Boolean.valueOf(customerData.get(3)),
                Boolean.valueOf(customerData.get(4)),
                customerData.get(5)
        );
    }

    public Customer() {
        this(null, null,null, null, null, null);
    }

    @Override
    public List<String> toList() {
        List<String> list = super.toList();
        list.add(phoneNo);
        return list;
    }

    @Override
    public boolean addNew() {
        if (super.addNew()) {
            String customerContent = String.format("%d;%s\n", this.getId(), phoneNo);
            return FileService.modifyFile(FILENAME, customerContent, true);
        }
        return false;
    }

    @Override
    public boolean update() {
        if (super.update()) {
            List<String> customerContent = List.of(String.valueOf(this.getId()), phoneNo);
            return FileService.updateSingleRow(FILENAME, customerContent, FileService.ID_COLUMN);
        }
        return false;
    }

    public static String register(String name, String password, String phoneNo) {
        String errorMessage = "";

        if (name.length() < 3) {
            errorMessage += "The total character of name must be more than or equal 4";
        }

        if (password.length() < 7) {
            errorMessage += "Minimum password length must be 8";
        }

        if (phoneNo.length() < 11) {
            errorMessage += "Phone number is not valid";
        }

        if (!errorMessage.isEmpty()) {
            return errorMessage;
        }

        Long id = FileService.getNewId(Customer.FILENAME);
        if (id == null) {
            return "File error, Customer file does not exist, please restart this system"; // add id also
        }
        if (id == -1) {
            return "ID error, file has invalid id, please delete or fix the file";
        }

        Customer customer = new Customer(id, name, password, false, false, phoneNo);
        customer.addNew();

        return "";
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public List<CartItem> getCart() {
        return cart;
    }

    public void setCart(List<CartItem> cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "\nCustomer{\n\t" +
                super.toString() + ",\n" +
                "\tphoneNo='" + phoneNo + "',\n" +
                "\tcart=" + cart +
                "\n}";
    }
}
