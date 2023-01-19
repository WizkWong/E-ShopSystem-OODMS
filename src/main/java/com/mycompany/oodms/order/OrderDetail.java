package com.mycompany.oodms.order;

import com.mycompany.oodms.FileService;
import com.mycompany.oodms.item.Item;

import java.util.ArrayList;
import java.util.List;

public class OrderDetail {
    // columns order in file: Order ID, Item ID, order price, quantity

    private static final String FILENAME = "order detail";

    private Item item;
    private Double orderPrice;
    private Integer quantity;

    public OrderDetail(Item item, Double orderPrice, Integer quantity) {
        this.item = item;
        this.orderPrice = orderPrice;
        this.quantity = quantity;
    }

    public static List<OrderDetail> getOrderDetail(Long orderId) {
        // get all the order item
        List<List<String>> orderDetailList = FileService.getMultipleSpecificData(FILENAME, FileService.ID_COLUMN, String.valueOf(orderId));
        // create a new array list to store order item
        List<OrderDetail> orderDetail = new ArrayList<>();
        for (List<String> itemRow : orderDetailList) {
            // get from item file
            List<String> item = FileService.getOneSpecificData(Item.FILENAME, FileService.ID_COLUMN, itemRow.get(1));
            // create new CartItem object then added into array
            orderDetail.add(new OrderDetail(new Item(item), Double.valueOf(itemRow.get(2)), Integer.valueOf(itemRow.get(3))));
        }
        return orderDetail;
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
