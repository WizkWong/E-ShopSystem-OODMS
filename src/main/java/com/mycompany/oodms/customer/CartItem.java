package com.mycompany.oodms.customer;

import com.mycompany.oodms.item.Item;
import com.mycompany.oodms.order.OrderDetail;

import java.util.List;

public class CartItem {
    private Item item;
    private Integer quantity;

    public CartItem(Item item, Integer quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public OrderDetail convertToOrderDetail() {
        return new OrderDetail(item, item.getPrice(), quantity);
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
