package com.mycompany.oodms.customer;

import com.mycompany.oodms.Dao.FileService;
import com.mycompany.oodms.Dao.ForeignKey;
import com.mycompany.oodms.OODMS;
import com.mycompany.oodms.item.Item;
import com.mycompany.oodms.item.ItemDao;

import java.util.ArrayList;
import java.util.List;

public class CartItemDao implements ForeignKey<CartItem> {
    // columns order in file: Customer ID, Item ID, quantity
    public static final String FILENAME = "cart";

    @Override
    public List<String> toList(CartItem cartItem, long customerId) {
        return new ArrayList<>(List.of(
                String.valueOf(customerId),
                String.valueOf(cartItem.getItem().getId()),
                String.valueOf(cartItem.getQuantity())
        ));
    }

    @Override
    public boolean fileAddNewRow(CartItem cartItem, long customerId) {
        List<String> cartItemData = toList(cartItem, customerId);
        return FileService.insertData(FILENAME, cartItemData);
    }

    @Override
    public boolean fileUpdate(CartItem cartItem, long customerId) {
        List<String> cartItemData = toList(cartItem, customerId);
        return FileService.updateSingleRow(FILENAME, cartItemData, 0, 1);
    }

    public boolean fileDeleteRow(CartItem cartItem, long customerId) {
        List<String> cartItemData = toList(cartItem, customerId);
        return FileService.deleteByTwoId(FILENAME, List.of(cartItemData));
    }

    public boolean fileDeleteRow(List<CartItem> cartItemList, long customerId) {
        CartItemDao cartItemDao = OODMS.getCartItemDao();
        List<List<String>> cartItemData = cartItemList.stream().map(cartItem -> cartItemDao.toList(cartItem, customerId)).toList();
        return FileService.deleteByTwoId(FILENAME, cartItemData);
    }

    // get all cart item
    public List<CartItem> getCartItem(Long customerId) {
        List<List<String>> cartItemList = FileService.getMultipleSpecificData(FILENAME, FileService.ID_COLUMN, String.valueOf(customerId));
        // create new array list to store cart item
        List<CartItem> cart = new ArrayList<>();
        for (List<String> itemRow : cartItemList) {
            // get from item file
            List<String> item = FileService.getOneSpecificData(ItemDao.ITEM_FILENAME, FileService.ID_COLUMN, itemRow.get(1));
            // create new CartItem object then added into array
            cart.add(new CartItem(new Item(item), Integer.valueOf(itemRow.get(2))));
        }
        return cart;
    }
}
