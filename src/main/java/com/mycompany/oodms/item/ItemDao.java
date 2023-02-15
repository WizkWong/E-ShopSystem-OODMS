package com.mycompany.oodms.item;

import com.mycompany.oodms.Dao.FileService;
import com.mycompany.oodms.Dao.ObjectDao;

import java.util.ArrayList;
import java.util.List;

public class ItemDao implements ObjectDao<Item> {
    // columns order in file: ID, name, category, price, stock, description
    public static final String FILENAME = "item";
    public static final String CATEGORY_FILENAME = "category";

    @Override
    public List<String> toList(Item item) {
        return new ArrayList<>(List.of(
                String.valueOf(item.getId()),
                item.getName(),
                item.getCategory(),
                String.valueOf(item.getPrice()),
                String.valueOf(item.getStock()),
                item.getDescription()
        ));
    }

    @Override
    public boolean fileAddNewRow(Item item) {
        List<String> itemData = toList(item);
        return FileService.insertData(FILENAME, itemData);
    }

    @Override
    public boolean fileUpdate(Item item) {
        List<String> itemData = toList(item);
        return FileService.updateSingleRow(FILENAME, itemData, FileService.ID_COLUMN);
    }

    public Item searchId(long id) {
        List<String> itemData = FileService.getOneSpecificData(ItemDao.FILENAME, FileService.ID_COLUMN, String.valueOf(id));
        if (itemData.isEmpty()) {
            return null;
        }
        return new Item(itemData);
    }

    public List<Item> getAll() {
        return FileService.readFile(ItemDao.FILENAME).stream().map(Item::new).toList();
    }

    public List<String> readCategory() {
        return FileService.readFile(ItemDao.CATEGORY_FILENAME).stream().map(list -> list.get(0)).toList();
    }

    public boolean addNewCategory(String categoryName) {
        return FileService.insertData(ItemDao.CATEGORY_FILENAME, List.of(categoryName));
    }

    public boolean modifyCategory(String oldCategoryName, String newCategoryName) {
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

    public boolean deleteCategory(String categoryName) {
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
}
