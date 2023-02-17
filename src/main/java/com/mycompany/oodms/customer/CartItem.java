package com.mycompany.oodms.customer;

import com.mycompany.oodms.FileService;
import com.mycompany.oodms.ForeignKey;
import com.mycompany.oodms.item.Item;
import com.mycompany.oodms.order.OrderDetail;

import java.util.ArrayList;
import java.util.List;

public class CartItem implements ForeignKey {
    // columns order in file: Customer ID, Item ID, quantity

    public static final String FILENAME = "cart";

    private Item item;
    private Integer quantity;

    public CartItem(Item item, Integer quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    @Override
    public List<String> toList() {
        return new ArrayList<>(List.of(
                String.valueOf(item.getId()),
                String.valueOf(quantity)
        ));
    }

    @Override
    public boolean fileAddNewRow(long foreignKeyId) {
        List<String> cartItemData = toList();
        cartItemData.add(0, String.valueOf(foreignKeyId));
        return FileService.insertData(FILENAME, cartItemData);
    }

    @Override
    public boolean fileUpdate(long foreignKeyId) {
        List<String> cartItemData = toList();
        cartItemData.add(0, String.valueOf(foreignKeyId));
        return FileService.updateSingleRow(FILENAME, cartItemData, 0, 1);
    }

    public boolean fileDeleteRow(long foreignKeyId) {
        List<String> cartItemData = toList();
        cartItemData.add(0, String.valueOf(foreignKeyId));
        return FileService.deleteByTwoId(FILENAME, List.of(cartItemData));
    }

    public OrderDetail convertToOrderDetail() {
        return new OrderDetail(item, item.getPrice(), quantity);
    }

    // get all cart item
    public static List<CartItem> getCartItem(Long customerId) {
        List<List<String>> cartItemList = FileService.getMultipleSpecificData(FILENAME, FileService.ID_COLUMN, String.valueOf(customerId));
        // create new array list to store cart item
        List<CartItem> cart = new ArrayList<>();
        for (List<String> itemRow : cartItemList) {
            // get from item file
            List<String> item = FileService.getOneSpecificData(Item.ITEM_FILENAME, FileService.ID_COLUMN, itemRow.get(1));
            // create new CartItem object then added into array
            cart.add(new CartItem(new Item(item), Integer.valueOf(itemRow.get(2))));
        }
        return cart;
    }

    // calculated total price = item price * quantity
    public static double calculateTotalPrice(List<CartItem> cartItemList) {
        double total = 0.0;
        for (CartItem cartItem : cartItemList) {
            total += cartItem.item.getPrice() * cartItem.quantity;
        }
        return total;
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
