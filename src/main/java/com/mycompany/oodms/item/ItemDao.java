package com.mycompany.oodms.item;

import com.mycompany.oodms.Dao.FileService;
import com.mycompany.oodms.Dao.ObjectDao;

import java.util.ArrayList;
import java.util.List;

public class ItemDao implements ObjectDao<Item> {
    // columns order in file: ID, name, category ID, price, stock, description
    public static final String ITEM_FILENAME = "item";
    public static final String CATEGORY_FILENAME = "category";

    @Override
    public List<String> toList(Item item) {
        return new ArrayList<>(List.of(
                String.valueOf(item.getId()),
                item.getName(),
                String.valueOf(item.getCategoryId()),
                String.valueOf(item.getPrice()),
                String.valueOf(item.getStock()),
                item.getDescription()
        ));
    }

    @Override
    public boolean fileAddNewRow(Item item) {
        List<String> itemData = toList(item);
        return FileService.insertData(ITEM_FILENAME, itemData);
    }

    @Override
    public boolean fileUpdate(Item item) {
        List<String> itemData = toList(item);
        return FileService.updateSingleRow(ITEM_FILENAME, itemData, FileService.ID_COLUMN);
    }

    public Item searchId(long id) {
        List<String> itemData = FileService.getOneSpecificData(ItemDao.ITEM_FILENAME, FileService.ID_COLUMN, String.valueOf(id));
        if (itemData.isEmpty()) {
            return null;
        }
        return new Item(itemData);
    }

    public List<Item> getAll() {
        return FileService.readFile(ItemDao.ITEM_FILENAME).stream().map(Item::new).toList();
    }

    public boolean addNewCategory(long categoryID, String categoryName) {
        List<String> categoryData = List.of(
                String.valueOf(categoryID),
                categoryName
        );
        return FileService.insertData(CATEGORY_FILENAME, categoryData);
    }

    public boolean modifyCategory(long categoryId, String newCategoryName) {
        List<String> list = List.of(
                String.valueOf(categoryId),
                newCategoryName
        );
        return FileService.updateSingleRow(CATEGORY_FILENAME, list, FileService.ID_COLUMN);
    }

    public boolean deleteCategory(long categoryId) {
        return FileService.removeById(CATEGORY_FILENAME, List.of(List.of(String.valueOf(categoryId))));
    }

    // get all category id and name
    public List<List<String>> getAllCategoryIdAndName() {
        return FileService.readFile(CATEGORY_FILENAME);
    }

    // get all category name
    public List<String> getAllCategoryName() {
        return FileService.readFile(CATEGORY_FILENAME).stream().map(list -> list.get(1)).toList();
    }

    // get category by id
    public String getCategoryNameById(long id) {
        List<String> list = FileService.getOneSpecificData(CATEGORY_FILENAME, FileService.ID_COLUMN, String.valueOf(id));
        if (list.isEmpty()) {
            return null;
        }
        // get category
        return list.get(1);
    }

    // get category id by category name
    public String getCategoryIdByName(String name) {
        List<List<String>> categoryList = FileService.readFile(CATEGORY_FILENAME);
        for (List<String> category : categoryList) {
            if (category.get(1).equals(name)) {
                return category.get(0);
            }
        }
        return name;
    }
}
