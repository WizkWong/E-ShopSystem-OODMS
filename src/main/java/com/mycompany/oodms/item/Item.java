package com.mycompany.oodms.item;

import com.mycompany.oodms.Dao.FileService;
import com.mycompany.oodms.OODMS;
import com.mycompany.oodms.customer.CartItem;

import java.util.List;
import java.util.regex.Pattern;

public class Item {
    private static final Pattern Product_Validation = Pattern.compile("[0-9!@#$%&*()_+=|<>?{}\\\\[\\\\]~-]");

    private Long id;
    private String name;
    private Long categoryId;
    private Double price;
    private Integer stock;
    private String description;

    public Item(Long id, String name, Long categoryId, Double price, Integer stock, String description) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.price = price;
        this.stock = stock;
        this.description = description;
    }

    public Item(List<String> itemData) {
        this(
                Long.valueOf(itemData.get(0)),
                itemData.get(1),
                Long.valueOf(itemData.get(2)),
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

    // Adding New Product
    public static String addItem(String name, String category, double price, int stock, String description) {

        // Get new id for product
        Long id = FileService.getNewId(ItemDao.ITEM_FILENAME);
        if (id == null || id == -1) {
            return "The system had met an error, please contact the technical support";
        }

        // Validate data
        String errorMessage = null;

        // Check if category is an id that already exist
        String CatIDExist = "no";
        List<List<String>> allCategory = FileService.readFile(ItemDao.CATEGORY_FILENAME);

        for (List<String> tempCategory : allCategory) {
            if (tempCategory.get(0).equals(category)) {
                CatIDExist = "yes";
                break;
            }
        }

        // If category is an id that already exist in category.txt, product category validation won't be needed
        if (CatIDExist.equals("yes")) {
            errorMessage = validateItem(name, "ProCatGood");
        } else {
            errorMessage = validateItem(name, category);
        }

        if (!errorMessage.isEmpty()) {
            return errorMessage;
        }

        Long categoryID;
        if (CatIDExist.equals("no")) {
            // Get new id for category if category does not exist
            categoryID = FileService.getNewId(ItemDao.CATEGORY_FILENAME);
            if (categoryID == null || categoryID == -1) {
                return "The system had met an error, please contact the technical support";
            }

            // Append category data into category.txt
            OODMS.getItemDao().addNewCategory(categoryID, name);

        } else {
            categoryID = Long.valueOf(category);
        }

        // Save item as an object
        Item item = new Item(id, name, categoryID, price, stock, description);

        // Append product data into item.txt
        OODMS.getItemDao().fileAddNewRow(item);

        return "";
    }

    // Validation for Adding New Product
    public static String validateItem(String ProName, String ProCat) {
        String errorMessage = "";

        // If product name or category contains character other than alphabet and space, there will be an error message
        if (Product_Validation.matcher(ProName).find()) {
            errorMessage += "ProName_Err";
        }
        if (Product_Validation.matcher(ProCat).find()) {
            errorMessage += "ProCat_Err2";
        }

        // Getting all the category and append it into a list
        List<List<String>> allCategory = FileService.readFile(ItemDao.CATEGORY_FILENAME);

        // If product category exist, there will be an error message
        boolean categoryExist = allCategory.stream().anyMatch(list -> list.get(1).equalsIgnoreCase(ProCat));
        if (categoryExist) {
            errorMessage += "ProCat_Err1";
        }

        return errorMessage;
    }

    // Validate Empty Text Field
    public static String validateEmpty(String ProName, String ProCat, String ProPrice, String ProStock, String ProDes) {
        if (ProName.isEmpty() || ProCat.isEmpty() || ProPrice.isEmpty() || ProStock.isEmpty() || ProDes.isEmpty()) {
            return "empty";
        }
        return "";
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategory(Long categoryId) {
        this.categoryId = categoryId;
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
                ", category='" + categoryId + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
