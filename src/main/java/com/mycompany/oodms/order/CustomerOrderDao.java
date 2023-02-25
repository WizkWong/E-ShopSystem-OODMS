package com.mycompany.oodms.order;

import com.mycompany.oodms.Dao.FileService;
import com.mycompany.oodms.Dao.ObjectDao;
import com.mycompany.oodms.OODMS;
import com.mycompany.oodms.customer.Customer;
import com.mycompany.oodms.deliveryStaff.DeliveryStaff;

import java.util.ArrayList;
import java.util.List;

public class CustomerOrderDao implements ObjectDao<CustomerOrder> {
    // columns order in file: CustomerOrder ID, Customer ID, Datetime
    public static final String FILENAME = "customer order";

    private final CustomerOrderPaymentDao customerOrderPaymentDao;
    private final DeliveryOrderDao deliveryOrderDao;
    private final OrderDetailDao orderDetailDao;

    public CustomerOrderDao(CustomerOrderPaymentDao customerOrderPaymentDao, OrderDetailDao orderDetailDao, DeliveryOrderDao deliveryOrderDao) {
        this.customerOrderPaymentDao = customerOrderPaymentDao;
        this.deliveryOrderDao = deliveryOrderDao;
        this.orderDetailDao = orderDetailDao;
    }

    @Override
    public List<String> toList(CustomerOrder customerOrder) {
        return new ArrayList<>(List.of(
                String.valueOf(customerOrder.getId()),
                String.valueOf(customerOrder.getCustomer().getId()),
                customerOrder.getStringOrderDateTime()
        ));
    }

    @Override
    public boolean fileAddNewRow(CustomerOrder customerOrder) {
        List<String> customerOrderData = toList(customerOrder);
        // save customer order
        if (!FileService.insertData(FILENAME, customerOrderData)) {
            return false;
        }
        // save customer order payment
        if (!customerOrderPaymentDao.fileAddNewRow(customerOrder.getCustomerOrderPayment())) {
            return false;
        }
        if (!deliveryOrderDao.fileAddNewRow(customerOrder.getDeliveryOrder())) {
            return false;
        }
        // save order detail
        return orderDetailDao.fileSaveAll(customerOrder.getOrderDetail(), customerOrder.getId());
    }

    @Override
    public boolean fileUpdate(CustomerOrder customerOrder) {
        System.out.println("Customer Order Class does not allow to update the file");
        return false;
    }

    // get all customer order by customer id
    public List<CustomerOrder> getById(Customer customer) {
        long id = customer.getId();
        return FileService.getMultipleSpecificData(FILENAME, 1, String.valueOf(id)).stream().map(CustomerOrder::new).toList();
    }
    
    public List<CustomerOrder> getAllbyDeliveryStaffId(DeliveryStaff deliverystaff) {
        Long id = deliverystaff.getId();
        List<CustomerOrder> customerOrderList = getAll();
        return customerOrderList.stream().filter(order -> {
            if (order.getDeliveryOrder().getDeliveryStaff() == null) {
                return false;
            }
            if (order.getDeliveryOrder().getDeliveryStaff().getId().equals(id)) {
                return true;
            }
            return false;
            }
        ).toList();
    }
    
    public List<CustomerOrder> getById(long CustomerOrderId) {     
        return FileService.getMultipleSpecificData(FILENAME, 0, String.valueOf(CustomerOrderId)).stream().map(CustomerOrder::new).toList();
    }
    
    public List<CustomerOrder> getAll() {
        return FileService.readFile(CustomerOrderDao.FILENAME).stream().map(CustomerOrder::new).toList();
    }
}
