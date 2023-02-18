package com.mycompany.oodms.order;

import com.mycompany.oodms.Dao.FileService;
import com.mycompany.oodms.Dao.ObjectDao;

import java.util.ArrayList;
import java.util.List;

public class CustomerOrderPaymentDao implements ObjectDao<CustomerOrderPayment> {
    // columns order in file: CustomerOrder-ID, type-of-payment, total-price, payment-datetime
    public static final String FILENAME = "customer order payment";

    @Override
    public List<String> toList(CustomerOrderPayment customerOrderPayment) {
        return new ArrayList<>(List.of(
                String.valueOf(customerOrderPayment.getCustomerOrder().getId()),
                customerOrderPayment.getTypeOfPayment(),
                String.valueOf(customerOrderPayment.getTotalPrice()),
                customerOrderPayment.getStringPaymentDateTime()
        ));
    }

    @Override
    public boolean fileAddNewRow(CustomerOrderPayment customerOrderPayment) {
        List<String> customerOrderPaymentData = toList(customerOrderPayment);
        return FileService.insertData(FILENAME, customerOrderPaymentData);
    }

    @Override
    public boolean fileUpdate(CustomerOrderPayment customerOrderPayment) {
        System.out.println("Customer Order Class does not allow to update the file");
        return false;
    }

    public CustomerOrderPayment getByCustomerOrderId(CustomerOrder customerOrder) {
        Long orderId = customerOrder.getId();
        List<String> customerOrderPaymentData = FileService.getOneSpecificData(FILENAME, FileService.ID_COLUMN, String.valueOf(orderId));
        return new CustomerOrderPayment(
                customerOrder,
                customerOrderPaymentData.get(1),
                Double.valueOf(customerOrderPaymentData.get(2)),
                customerOrderPaymentData.get(3)
        );
    }
}
