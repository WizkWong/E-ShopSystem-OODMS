package com.mycompany.oodms.customer;

import com.mycompany.oodms.Dao.FileService;
import com.mycompany.oodms.OODMS;
import com.mycompany.oodms.item.Item;
import com.mycompany.oodms.order.CustomerOrder;
import com.mycompany.oodms.order.CustomerOrderDao;
import com.mycompany.oodms.user.User;
import com.mycompany.oodms.user.UserDao;

import java.util.List;
import java.util.Optional;

public class Customer extends User {
    public static final String FILENAME = "customer";

    private List<CartItem> cart;

    private final CartItemDao cartItemDao;
    private final CustomerOrderDao customerOrderDao;

    public Customer(Long id, String username, String password, String email, String phoneNo, Boolean staff, Boolean admin) {
        super(id, username, password, email, phoneNo, staff, admin);
        cartItemDao = OODMS.getCartItemDao();
        this.cart = cartItemDao.getCartItem(id);
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
            System.out.println("Item is already exist in cart, cannot add in it");
            return false;
        }
        CartItem cartItem = new CartItem(item, quantity);
        this.cart.add(cartItem);
        // add new cart item data into cart file
        return cartItemDao.fileAddNewRow(cartItem, this.getId());
    }

    // only use when cart item quantity is change
    public boolean updateCartItem(List<CartItem> cart) {
        List<Long> cartItemId = cart.stream().map(cartItem -> cartItem.getItem().getId()).toList();
        int match = 0;
        for (CartItem cartItem : this.cart) {
            for (Long id : cartItemId) {
                if (cartItem.getItem().getId().equals(id)) {
                    match++;
                    break;
                }
            }
        }
        // check the current cart is same size as new cart
        if (this.cart.size() != match) {
            System.out.println("New cart item does not match with current cart item");
            return false;
        }
        this.cart = cart;
        return cartItemDao.updateCart(cart, getId());
    }

    // delete cart item
    public boolean deleteCartItem(Item item) {
        Optional<CartItem> existCartItem = this.cart.stream().filter(cartItem -> cartItem.getItem().getId().equals(item.getId())).findFirst();
        if (existCartItem.isEmpty()) {
            System.out.println("Item is not exist in cart");
            return false;
        }
        CartItem cartItem = existCartItem.get();
        this.cart.remove(cartItem);
        // delete the cart data
        return cartItemDao.fileDeleteRow(cartItem, this.getId());
    }

    // create and save new customer order
    public boolean checkOut(String typeOfPayment) {
        // get new id
        Long id = FileService.getNewId(CustomerOrderDao.FILENAME);
        if (id == null || id == -1) {
            return false;
        }
        CustomerOrder customerOrder = new CustomerOrder(id, typeOfPayment, this);
        // save the order including the order payment, delivery order and order detail
        if (customerOrderDao.fileAddNewRow(customerOrder)) {
            this.cart.clear();
            return true;
        }
        return false;
    }

    // register a new account
    public static String register(String name, String password1, String password2, String email, String phoneNo) {
        // validate the name, password and phone number
        String errorMessage = User.validate(name, password1, password2, email, phoneNo);

        if (name.length() >= 4) {
            // get all user
            List<List<String>> allUser = FileService.readFile(UserDao.FILENAME);
            // find any username ignore case match
            boolean usernameTaken = allUser.stream().anyMatch(list -> list.get(1).equalsIgnoreCase(name));

            // check username taken
            if (usernameTaken) {
                errorMessage += "Username taken";
            }
        }

        // if error message is not empty then return error message
        if (!errorMessage.isEmpty()) {
            return errorMessage;
        }

        // get new id
        Long id = FileService.getNewId(UserDao.FILENAME);
        if (id == null || id == -1) {
            return "The system had met an error, please contact the technical support";
        }

        Customer customer = new Customer(id, name, password1, email, phoneNo, false, false);
        // save new customer data
        OODMS.getCustomerDao().fileAddNewRow(customer);

        return "";
    }

    // get the customer data by customer id
    public static Customer getCustomerById(long id) {
        String idString = String.valueOf(id);
        List<String> userData = FileService.getOneSpecificData(UserDao.FILENAME, FileService.ID_COLUMN, idString);
        List<String> customerData = FileService.getOneSpecificData(Customer.FILENAME, FileService.ID_COLUMN, idString);
        customerData = User.joinWithUser(customerData, userData);
        if (customerData == null) {
            return null;
        }
        return new Customer(customerData);
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
