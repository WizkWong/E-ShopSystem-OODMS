package com.mycompany.oodms.order;

import com.mycompany.oodms.Dao.FileService;
import com.mycompany.oodms.Dao.ObjectDao;
import com.mycompany.oodms.OODMS;
import com.mycompany.oodms.deliveryStaff.DeliveryStaff;
import static com.mycompany.oodms.order.CustomerOrderDao.FILENAME;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeliveryOrderDao implements ObjectDao<DeliveryOrder> {
    // note: null value will be store
    // columns order in file: CustomerOrder ID, Delivery Staff ID, Delivery Status, Address, Assign Date Time, Delivered Date Time, Feedback
    public static final String FILENAME = "delivery order";

    @Override
    public List<String> toList(DeliveryOrder deliveryOrder) {
        // cannot use List.of() cause List does not allow null value store
        return new ArrayList<>(Arrays.asList(
                String.valueOf(deliveryOrder.getCustomerOrder().getId()),
                String.valueOf(deliveryOrder.getDeliveryStaff().getId()),
                deliveryOrder.getDeliveryStatus().getStatus(),
                deliveryOrder.getAddress(),
                deliveryOrder.getStringAssignDateTime(),
                deliveryOrder.getStringDeliveredDateTime(),
                deliveryOrder.getFeedback()
        ));
    }

    @Override
    public boolean fileAddNewRow(DeliveryOrder deliveryOrder) {
        List<String> deliveryOrderData = toList(deliveryOrder);
        return FileService.insertData(FILENAME, deliveryOrderData);
    }

    @Override
    public boolean fileUpdate(DeliveryOrder deliveryOrder) {
        List<String> deliveryOrderData = toList(deliveryOrder);
        return FileService.updateSingleRow(FILENAME, deliveryOrderData, FileService.ID_COLUMN);
    }

    public DeliveryOrder getByCustomerOrderId(CustomerOrder customerOrder) {
        Long orderId = customerOrder.getId();
        List<String> deliveryOrderData = FileService.getOneSpecificData(FILENAME, FileService.ID_COLUMN, String.valueOf(orderId));
        return new DeliveryOrder(customerOrder, deliveryOrderData.get(1), deliveryOrderData.get(2), deliveryOrderData.get(3), deliveryOrderData.get(4), deliveryOrderData.get(5), deliveryOrderData.get(6));
    }
}
