package com.mycompany.oodms.customer;

import com.mycompany.oodms.FileService;
import com.mycompany.oodms.item.Item;

import java.util.ArrayList;
import java.util.List;

public class CartItem {
    public static final String FILENAME = "cart";
    private Item item;
    private Integer quantity;

    public CartItem(Item item, Integer quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public static List<CartItem> getCartItem(Long customerId) {
        // get all cart item
        List<List<String>> cartItem = FileService.getMultipleSpecificData(FILENAME, 0, String.valueOf(customerId));
        // create new array to store cart item
        List<CartItem> cart = new ArrayList<>();
        for (List<String> itemRow : cartItem) {
            // get from item file
            List<String> item = FileService.getOneSpecificData(Item.FILENAME, 0, itemRow.get(1));
            // create new CartItem object then added into array
            cart.add(new CartItem(new Item(item), Integer.valueOf(itemRow.get(2))));
        }
        return cart;
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

    @Override
    public String toString() {
        return "\n\t\tCartItem{" +
                " item = " + item +
                ", quantity=" + quantity +
                "}";
    }
}
