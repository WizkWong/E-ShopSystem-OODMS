package com.mycompany.oodms.item;

import com.mycompany.oodms.user.User;
import com.mycompany.oodms.user.UserAuthn;

import java.util.List;

public class Item {
    public static final String FILENAME = "item";

    private Long id;
    private String name;
    private String category;
    private Double price;
    private Integer stock;

    public Item(Long id, String name, String category, Double price, Integer stock) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    public Item(List<String> itemData) {
        this(
                Long.valueOf(itemData.get(0)),
                itemData.get(1),
                itemData.get(2),
                Double.valueOf(itemData.get(3)),
                Integer.valueOf(itemData.get(4))
        );
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
