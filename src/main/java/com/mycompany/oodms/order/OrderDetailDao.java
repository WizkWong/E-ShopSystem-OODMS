package com.mycompany.oodms.order;

import com.mycompany.oodms.Dao.FileService;
import com.mycompany.oodms.item.Item;
import com.mycompany.oodms.item.ItemDao;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailDao {
    // columns order in file: Order-ID, Item-ID, order-price, quantity
    public static final String FILENAME = "order detail";

    public List<String> toList(OrderDetail orderDetail, long orderId) {
        return new ArrayList<>(List.of(
                String.valueOf(orderId),
                String.valueOf(orderDetail.getItem().getId()),
                String.valueOf(orderDetail.getOrderPrice()),
                String.valueOf(orderDetail.getQuantity())
        ));
    }

    // get all the order item
    public List<OrderDetail> getById(long id) {
        List<List<String>> orderDetailList = FileService.getMultipleSpecificData(FILENAME, FileService.ID_COLUMN, String.valueOf(id));
        // create a new array list to store order item
        List<OrderDetail> orderDetail = new ArrayList<>();
        for (List<String> itemRow : orderDetailList) {
            // get from item file
            List<String> item = FileService.getOneSpecificData(ItemDao.ITEM_FILENAME, FileService.ID_COLUMN, itemRow.get(1));
            // create new CartItem object then added into array
            orderDetail.add(new OrderDetail(new Item(item), Double.valueOf(itemRow.get(2)), Integer.valueOf(itemRow.get(3))));
        }
        return orderDetail;
    }

    // save all order detail into file
    public boolean fileSaveAll(List<OrderDetail> orderDetail, long id) {
        List<List<String>> orderDetailStringList = orderDetail.stream().map(orderD -> toList(orderD, id)).toList();
        return FileService.insertMultipleData(OrderDetailDao.FILENAME, orderDetailStringList);
    }
}
