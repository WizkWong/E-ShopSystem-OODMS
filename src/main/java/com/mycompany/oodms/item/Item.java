package com.mycompany.oodms.item;

import com.mycompany.oodms.customer.CartItem;

import java.util.List;

public class Item {
    private Long id;
    private String name;
    private String category;
    private Double price;
    private Integer stock;
    private String description;

    public Item(Long id, String name, String category, Double price, Integer stock, String description) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.description = description;
    }

    public Item(List<String> itemData) {
        this(
                Long.valueOf(itemData.get(0)),
                itemData.get(1),
                itemData.get(2),
                Double.valueOf(itemData.get(3)),
                Integer.valueOf(itemData.get(4)),
                itemData.get(5)
        );
    }

    public boolean checkStockAvailable() {
        return this.stock > 0;
    }

    public void plusStock(CartItem cartItem) {
        if (this.name.equals(cartItem.getItem().getName())) {
            this.stock += cartItem.getQuantity();
        }
    }

    public void minusStock(CartItem cartItem) {
        if (this.name.equals(cartItem.getItem().getName())) {
            this.stock -= cartItem.getQuantity();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
