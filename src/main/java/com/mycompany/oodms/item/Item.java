package com.mycompany.oodms.item;

import com.mycompany.oodms.FileService;

import java.util.List;

public class Item {
    public static final String FILENAME = "item";
    public static final String CATEGORY_FILENAME = "category";

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

    public boolean checkStockAvailable() {
        return this.stock > 0;
    }

    public static Item searchId(int id) {
        return new Item(FileService.getOneSpecificData(FILENAME, FileService.ID_COLUMN, String.valueOf(id)));
    }

    public static boolean addNewCategory(String categoryName) {
        return FileService.insertData(CATEGORY_FILENAME, List.of(categoryName));
    }

    public static boolean modifyCategory(String oldCategoryName, String newCategoryName) {
        List<List<String>> allCategory = FileService.readFile(CATEGORY_FILENAME);
        String content = "";
        for (List<String> category : allCategory) {
            if (category.get(0).equals(oldCategoryName)) {
                content += newCategoryName + "\n";
                continue;
            }
            content += category.get(0) + "\n";
        }
        return FileService.modifyFile(CATEGORY_FILENAME, content, false);
    }

    public static boolean deleteCategory(String categoryName) {
        List<List<String>> allCategory = FileService.readFile(CATEGORY_FILENAME);
        String content = "";
        for (List<String> category : allCategory) {
            if (category.get(0).equals(categoryName)) {
                continue;
            }
            content += category.get(0) + "\n";
        }
        return FileService.modifyFile(CATEGORY_FILENAME, content, false);
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
