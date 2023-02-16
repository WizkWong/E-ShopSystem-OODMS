package com.mycompany.oodms.order;

import com.mycompany.oodms.OODMS;
import com.mycompany.oodms.customer.CartItem;
import com.mycompany.oodms.customer.Customer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CustomerOrder {
    private static final DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    private Long id;
    private Customer customer;
    private LocalDateTime orderDateTime;
    private List<OrderDetail> orderDetail;
    private CustomerOrderPayment customerOrderPayment;

    public CustomerOrder(Long id, String typeOfPayment, Customer customer) {
        this.id = id;
        this.customer = customer;
        this.orderDateTime = LocalDateTime.now();
        this.orderDetail = customer.getCart().stream().map(CartItem::convertToOrderDetail).toList();
        this.customerOrderPayment = new CustomerOrderPayment(this, typeOfPayment, OrderDetail.calculateTotalPrice(this.orderDetail));
    }

    public CustomerOrder(Long id, Customer customer, LocalDateTime orderDateTime) {
        this.id = id;
        this.customer = customer;
        this.orderDateTime = orderDateTime;
        this.orderDetail = OODMS.getOrderDetailDao().getOrderDetailByOrderId(id);
        this.customerOrderPayment = OODMS.getCustomerOrderPaymentDao().getCustomerOrderPaymentById(this);
    }

    public CustomerOrder(Long id, Customer customer, String orderDateTime) {
        this(id, customer, LocalDateTime.parse(orderDateTime,formatDateTime));
    }

    public CustomerOrder(List<String> customerOrderData) {
        this(
                Long.valueOf(customerOrderData.get(0)),
                OODMS.getCustomerDao().getById(Long.parseLong(customerOrderData.get(1))),
                customerOrderData.get(2)
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public String getStringOrderDateTime() {
        return orderDateTime.format(formatDateTime);
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public List<OrderDetail> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetail> orderDetail) {
        this.orderDetail = orderDetail;
    }

    public CustomerOrderPayment getCustomerOrderPayment() {
        return customerOrderPayment;
    }

    public void setCustomerOrderPayment(CustomerOrderPayment customerOrderPayment) {
        this.customerOrderPayment = customerOrderPayment;
    }
}
