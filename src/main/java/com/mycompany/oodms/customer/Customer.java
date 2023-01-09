package com.mycompany.oodms.customer;

import com.mycompany.oodms.FileService;
import com.mycompany.oodms.user.User;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    public static final String FILENAME = "customer";
    public static final String CART_FILENAME = "cart";

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

    public List<String> toList() {
        return new ArrayList<>(List.of(
                String.valueOf(this.getId()),
                this.getUsername(),
                this.getPassword(),
                String.valueOf(this.getStaff()),
                String.valueOf(this.getAdmin()),
                String.valueOf(this.getPhoneNo())
        ));
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

        Long id = FileService.getLastId(Customer.FILENAME);
        if (id == null) {
            return "File error, Customer file does not exist, please restart this system"; // add id also
        }
        if (id == 2) {
            return "ID error, file has invalid id, please delete or fix the file";
        }

        String userContent = String.format("%d;%s;%s;0;0", ++id, name, password);
        FileService.modifyFile(User.USER_FILENAME, userContent, true);

        String customerContent = String.format("%d;%s", ++id, phoneNo);
        FileService.modifyFile(Customer.FILENAME, customerContent, true);

        return "";
    }

    public static List<String> joinWithUser(List<String> customerData, List<String> userData) {
        if (customerData.get(0).equals(userData.get(0))) {
            customerData.remove(0);
            userData.addAll(customerData);
            return userData;
        }
        System.out.println("Id does not match, fail to join list");
        return null;
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
