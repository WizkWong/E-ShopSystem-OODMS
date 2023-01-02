package com.mycompany.oodms.customer;

import com.mycompany.oodms.item.Item;
import com.mycompany.oodms.user.User;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    public static final String FILENAME = "customer";

    private String phoneNo;
    private String address;
    private List<CartItem> cart;

    public Customer(Long id, String username, String password, Boolean staff, Boolean admin, String phoneNo, String address) {
        super(id, username, password, staff, admin);
        this.phoneNo = phoneNo;
        this.address = address;
        this.cart = new ArrayList<>();
    }

    public Customer() {
        this(null, null,null, null, null, null, null);
    }

    static class CartItem {
        private Item item;
        private Integer quantity;

        public CartItem(Item item, Integer quantity) {
            this.item = item;
            this.quantity = quantity;
        }

        public Item getItem() {
            return item;
        }

        public void setItem(Item item) {
            this.item = item;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }
    }

    public static Customer listToCustomer(List<String> customerData) {
        return new Customer(
                Long.parseLong(customerData.get(0)),
                customerData.get(1),
                customerData.get(2),
                Boolean.parseBoolean(customerData.get(3)),
                Boolean.parseBoolean(customerData.get(4)),
                customerData.get(5),
                customerData.get(6)
        );
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<CartItem> getCart() {
        return cart;
    }

    public void setCart(List<CartItem> cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "Customer{ " +
                super.toString() + ", " +
                "phoneNo='" + phoneNo + '\'' +
                ", address='" + address + '\'' +
                ", cart=" + cart +
                '}';
    }
}
