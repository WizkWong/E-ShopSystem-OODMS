package com.mycompany.oodms.order;

import com.mycompany.oodms.customer.Customer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CustomerOrder {
    private static final String FILENAME = "customer order";
    private final DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private Long id;
    private Customer customer;
    private LocalDateTime orderDateTime;
    private List<OrderDetail> orderDetail;

    public CustomerOrder(Long id, Customer customer, LocalDateTime orderDateTime) {
        this.id = id;
        this.customer = customer;
        this.orderDateTime = orderDateTime;
        this.orderDetail = OrderDetail.getOrderDetail(id);
    }

    public CustomerOrder(Long id, Customer customer, String orderDateTime) {
        this.id = id;
        this.customer = customer;
        this.orderDateTime = LocalDateTime.parse(orderDateTime, formatDateTime);
        this.orderDetail = OrderDetail.getOrderDetail(id);
    }

//    public CustomerOrder(List<String> customerOrderData) {
//        this(
//                Long.valueOf(customerOrderData.get(0)),
//                customerOrderData.get(1),
//                customerOrderData.get(2)
//        )
//    }

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
}
