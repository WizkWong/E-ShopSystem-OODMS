package com.mycompany.oodms.item;

import com.mycompany.oodms.Dao.FileService;
import static com.mycompany.oodms.customer.Customer.FILENAME;
import static com.mycompany.oodms.user.User.USER_FILENAME;
import com.mycompany.oodms.customer.CartItem;

import java.util.List;
import java.util.regex.Pattern;

public class Item {
    public static final String ITEM_FILENAME = "item";
    public static final String CATEGORY_FILENAME = "category";
    public static final int CATEGORY_COLUMN_NUM = 2;
    private static final Pattern Product_Validation = Pattern.compile("[0-9!@#$%&*()_+=|<>?{}\\\\[\\\\]~-]");

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

    public static boolean addNewCategory(String categoryID, String categoryName) {
        List<String> categoryData = List.of(
                categoryID,
                categoryName
        );
        return FileService.insertData(CATEGORY_FILENAME, categoryData);
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

    // Adding New Product
    public static String addItem(String ProName, String ProCat, double ProPrice, int ProStock, String ProDes) {

        // Get new id for product
        Long id = FileService.getNewId(ITEM_FILENAME);
        if (id == null || id == -1) {
            return "The system had met an error, please contact the technical support";
        }

        // Validate data
        String errorMessage = null;

        // Check if ProCat is an id that already exist
        String CatIDExist = "no";
        List<List<String>> allCategory = FileService.readFile(CATEGORY_FILENAME);
        for (List category:allCategory) {
            if (category.get(0).toString().equals(ProCat)) {
                CatIDExist = "yes";
            }
        }

        // If ProCat is an id that already exist in category.txt, product category validation won't be needed
        if (CatIDExist.equals("yes")) {
            errorMessage = validateItem(ProName, "ProCatGood");
        } else {
            errorMessage = validateItem(ProName, ProCat);
        }

        if (!errorMessage.isEmpty()) {
            return errorMessage;
        }

        String CategoryID = "";
        if (CatIDExist.equals("no")) {
            // Get new id for category if category does not exist
            Long Catid = FileService.getNewId(CATEGORY_FILENAME);
            if (Catid == null || Catid == -1) {
                return "The system had met an error, please contact the technical support";
            }

            // Append category data into category.txt
            addNewCategory(Catid.toString(), ProName);

            CategoryID = Catid.toString();
        } else {
            CategoryID = ProCat;
        }

        // Save item as an object
        Item Product = new Item(id, ProName, CategoryID, ProPrice, ProStock, ProDes);

        // Append product data into item.txt
        Product.fileAddNewRow();

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
        List<List<String>> allCategory = FileService.readFile(CATEGORY_FILENAME);

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
