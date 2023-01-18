package com.mycompany.oodms.customer;

import com.mycompany.oodms.FileService;
import com.mycompany.oodms.OODMS;
import com.mycompany.oodms.item.Item;
import com.mycompany.oodms.user.User;

import java.util.List;
import java.util.Optional;

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
    public boolean fileAddNewRow() {
        if (super.fileAddNewRow()) {
            List<String> customerData = List.of(
                    String.valueOf(getId()),
                    phoneNo
            );
            return FileService.insertData(FILENAME, customerData);
        }
        return false;
    }

    @Override
    public boolean fileUpdate() {
        if (!super.fileUpdate()) {
            List<String> customerData = List.of(
                    String.valueOf(getId()),
                    phoneNo
            );
            return !FileService.updateSingleRow(FILENAME, customerData, FileService.ID_COLUMN);
        }
        return true;
    }

    public void addCartItem(Item item, int quantity) {
        Customer customer = (Customer) OODMS.currentUser;
        Optional<CartItem> existCartItem = customer.getCart().stream().filter(cartItem -> cartItem.getItem().getId().equals(item.getId())).findFirst();
        if (existCartItem.isPresent()) {
            System.out.println("Item is already exist in cart, cannot add in it");
            return;
        }
        CartItem cartItem = new CartItem(item, quantity);
        this.cart.add(cartItem);
        if (!cartItem.fileAddNewRow(customer.getId())) {
            System.out.println("Add failed");
        }
    }

    public void deleteCartItem(CartItem cartItem) {
        this.cart.remove(cartItem);
        // delete the file
    }

    public void updateCartItem(Item item, int quantity) {
        Customer customer = (Customer) OODMS.currentUser;
        Optional<CartItem> existCartItem = customer.getCart().stream().filter(cartItem -> cartItem.getItem().getId().equals(item.getId())).findFirst();
        if (existCartItem.isEmpty()) {
            System.out.println("Item is not exist in cart");
            return;
        }
        CartItem cartItem = existCartItem.get();
        cartItem.setQuantity(quantity);
        if (cartItem.fileUpdate(customer.getId())) {
            System.out.println("Update failed");
        }
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
        customer.fileAddNewRow();

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
