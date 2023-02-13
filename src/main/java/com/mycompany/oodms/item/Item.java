package com.mycompany.oodms.item;

import com.mycompany.oodms.Dao.FileService;

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

    public static Item searchId(long id) {
        List<String> itemData = FileService.getOneSpecificData(ItemDao.FILENAME, FileService.ID_COLUMN, String.valueOf(id));
        if (itemData.isEmpty()) {
            return null;
        }
        return new Item(itemData);
    }

    public static List<String> readCategory() {
        return FileService.readFile(ItemDao.CATEGORY_FILENAME).stream().map(list -> list.get(0)).toList();
    }

    public static boolean addNewCategory(String categoryName) {
        return FileService.insertData(ItemDao.CATEGORY_FILENAME, List.of(categoryName));
    }

    public static boolean modifyCategory(String oldCategoryName, String newCategoryName) {
        List<List<String>> allCategory = FileService.readFile(ItemDao.CATEGORY_FILENAME);
        String content = "";
        for (List<String> category : allCategory) {
            if (category.get(0).equals(oldCategoryName)) {
                content += newCategoryName + "\n";
                continue;
            }
            content += category.get(0) + "\n";
        }
        return FileService.modifyFile(ItemDao.CATEGORY_FILENAME, content, false);
    }

    public static boolean deleteCategory(String categoryName) {
        List<List<String>> allCategory = FileService.readFile(ItemDao.CATEGORY_FILENAME);
        String content = "";
        for (List<String> category : allCategory) {
            if (category.get(0).equals(categoryName)) {
                continue;
            }
            content += category.get(0) + "\n";
        }
        return FileService.modifyFile(ItemDao.CATEGORY_FILENAME, content, false);
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
