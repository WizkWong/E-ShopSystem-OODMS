package com.mycompany.oodms.item;

import com.mycompany.oodms.Dao.ObjectDao;

import java.util.List;

public class ItemDao implements ObjectDao<Item> {
    // columns order in file: ID, name, category, price, stock, description
    public static final String FILENAME = "item";
    public static final String CATEGORY_FILENAME = "category";

    @Override
    public List<String> toList(Item item) {
        return null;
    }

    @Override
    public boolean fileAddNewRow(Item item) {
        return false;
    }

    @Override
    public boolean fileUpdate(Item item) {
        return false;
    }
}
