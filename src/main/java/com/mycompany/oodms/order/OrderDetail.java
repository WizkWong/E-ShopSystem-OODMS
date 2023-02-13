package com.mycompany.oodms.order;

import com.mycompany.oodms.item.Item;

import java.util.List;

public class OrderDetail {
    private Item item;
    private Double orderPrice;
    private Integer quantity;

    public OrderDetail(Item item, Double orderPrice, Integer quantity) {
        this.item = item;
        this.orderPrice = orderPrice;
        this.quantity = quantity;
    }

    // calculated total price = item price had order * quantity
    public static double calculateTotalPrice(List<OrderDetail> orderDetailList) {
        double total = 0.0;
        for (OrderDetail orderDetail : orderDetailList) {
            total += orderDetail.orderPrice * orderDetail.quantity;
        }
        return total;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
