package com.mycompany.oodms.customer;

import com.mycompany.oodms.Dao.FileService;
import com.mycompany.oodms.OODMS;
import com.mycompany.oodms.item.Item;
import com.mycompany.oodms.item.ItemDao;
import com.mycompany.oodms.order.CustomerOrder;
import com.mycompany.oodms.order.CustomerOrderDao;
import com.mycompany.oodms.user.User;
import com.mycompany.oodms.user.UserDao;

import java.util.*;

public class Customer extends User {
    public static final String FILENAME = "customer";

    private final List<CartItem> cart;

    private final CartItemDao cartItemDao;
    private final ItemDao itemDao;
    private final CustomerOrderDao customerOrderDao;

    public Customer(Long id, String username, String password, String email, String phoneNo, Boolean staff, Boolean admin) {
        super(id, username, password, email, phoneNo, staff, admin);
        cartItemDao = OODMS.getCartItemDao();
        this.cart = cartItemDao.getCartItem(id);
        itemDao = OODMS.getItemDao();
        customerOrderDao = OODMS.getCustomerOrderDao();
    }

    public Customer(List<String> customerData) {
        this(
                Long.valueOf(customerData.get(0)),
                customerData.get(1),
                customerData.get(2),
                customerData.get(3),
                customerData.get(4),
                Boolean.valueOf(customerData.get(5)),
                Boolean.valueOf(customerData.get(6))
        );
    }

    public Customer() {
        this(null, null,null, null, null, null, null);
    }

    public boolean checkItemExistInCart(Item item) {
        Optional<CartItem> existCartItem = this.cart.stream().filter(cartItem -> cartItem.getItem().getId().equals(item.getId())).findFirst();
        return existCartItem.isPresent();
    }

    // adding new cart item
    public boolean addCartItem(Item item, int quantity) {
        Optional<CartItem> existCartItem = this.cart.stream().filter(cartItem -> cartItem.getItem().getId().equals(item.getId())).findFirst();
        if (existCartItem.isPresent()) {
            System.out.println("Item already exist in cart, cannot add in it");
            return false;
        }
        CartItem cartItem = new CartItem(item, quantity);
        this.cart.add(cartItem);
        item.minusStock(cartItem);
        // add new cart item data into cart file and update the item stock
        return cartItemDao.fileAddNewRow(cartItem, this.getId()) && itemDao.fileUpdate(item);
    }

    // only use when cart item quantity is change
    public boolean updateCartItem(Item item, int quantity) {
        Optional<CartItem> existCartItem = this.cart.stream().filter(cartItem -> cartItem.getItem().getId().equals(item.getId())).findFirst();
        if (existCartItem.isEmpty()) {
            System.out.println("Cannot update the item does not exist in cart");
            return false;
        }
        CartItem cartItem = existCartItem.get();
        item.plusStock(cartItem);
        cartItem.setQuantity(quantity);
        item.minusStock(cartItem);
        // add new cart item data into cart file and update the item stock
        return cartItemDao.fileUpdate(cartItem, this.getId()) && itemDao.fileUpdate(item);
    }

    // delete cart item
    public boolean deleteCartItem(Item item) {
        Optional<CartItem> existCartItem = this.cart.stream().filter(cartItem -> cartItem.getItem().getId().equals(item.getId())).findFirst();
        if (existCartItem.isEmpty()) {
            System.out.println("Item is not exist in cart");
            return false;
        }
        CartItem cartItem = existCartItem.get();
        item.plusStock(cartItem);
        this.cart.remove(cartItem);
        // delete the cart data
        return cartItemDao.fileDeleteRow(cartItem, this.getId()) && itemDao.fileUpdate(item);
    }

    // delete cart item
    public void clearCartItem() {
        List<CartItem> cartList = new ArrayList<>(this.cart);
        this.cart.clear();
        // delete the cart data
        if (cartItemDao.fileDeleteRow(cartList, this.getId())) {
            System.out.println("Fail to remove all Customer Cart Item");
        }
    }

    // create and save new customer order
    public String checkOut(String typeOfPayment, String unitNo, String street, String city, String postalCode, String state) {
        String errorMsg = "";
        if (unitNo.isEmpty()) {
            errorMsg += "Unit No is empty;";
        }
        if (street.isEmpty()) {
            errorMsg += "Street is empty;";
        }
        if (city.isEmpty()) {
            errorMsg += "City is empty;";
        }
        if (postalCode.isEmpty()) {
            errorMsg += "Postal Code is empty;";
        }
        if (state.isEmpty()) {
            errorMsg += "State is empty;";
        }

        if (!errorMsg.isEmpty()) {
            return errorMsg;
        }
        // get new id
        Long id = FileService.getNewId(CustomerOrderDao.FILENAME);
        if (id == null || id == -1) {
            return "System Error";
        }
        String address = unitNo + ", " + street + ", " + city + " " + postalCode + ", " + state;
        CustomerOrder customerOrder = new CustomerOrder(id, typeOfPayment, this, address);
        // save the order including the order payment, delivery order and order detail
        if (!customerOrderDao.fileAddNewRow(customerOrder)) {
            return "System Error";
        }
        return "";
    }

    // register a new account
    public static String register(String name, String password1, String password2, String email, String phoneNo) {
        // validate the name, password and phone number
        String errorMessage = User.validate(name, password1, password2, email, phoneNo) + User.checkUserExist(name);

        // if error message is not empty then return error message
        if (!errorMessage.isEmpty()) {
            return errorMessage;
        }

        // get new id
        Long id = FileService.getNewId(UserDao.FILENAME);
        if (id == null || id == -1) {
            return "System Error";
        }

        Customer customer = new Customer(id, name, password1, email, phoneNo, false, false);
        // save new customer data
        if (!OODMS.getCustomerDao().fileAddNewRow(customer)) {
            return "System Error";
        }

        return "";
    }

    public List<CartItem> getCart() {
        return cart;
    }

    @Override
    public String toString() {
        return "\nCustomer{\n\t" +
                super.toString() + ",\n" +
                "\tcart=" + cart +
                "\n}";
    }
}
