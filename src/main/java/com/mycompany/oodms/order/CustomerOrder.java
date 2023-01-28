package com.mycompany.oodms.order;

import com.mycompany.oodms.FileService;
import com.mycompany.oodms.customer.Customer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CustomerOrder implements FileService {
    // columns order in file: CustomerOrder ID, Customer ID, Datetime

    public static final String FILENAME = "customer order";
    private final DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private Long id;
    private Customer customer;
    private LocalDateTime orderDateTime;
    private List<OrderDetail> orderDetail;
    private CustomerOrderPayment customerOrderPayment;

    public CustomerOrder(Long id, Customer customer, LocalDateTime orderDateTime) {
        this.id = id;
        this.customer = customer;
        this.orderDateTime = orderDateTime;
        this.orderDetail = OrderDetail.getOrderDetailByOrderId(id);
        this.customerOrderPayment = CustomerOrderPayment.getCustomerOrderPaymentById(this);
    }

    public CustomerOrder(Long id, Customer customer, String orderDateTime) {
        this.id = id;
        this.customer = customer;
        this.orderDateTime = LocalDateTime.parse(orderDateTime, formatDateTime);
        this.orderDetail = OrderDetail.getOrderDetailByOrderId(id);
        this.customerOrderPayment = CustomerOrderPayment.getCustomerOrderPaymentById(this);
    }

    public CustomerOrder(List<String> customerOrderData) {
        this(
                Long.valueOf(customerOrderData.get(0)),
                Customer.getCustomerById(Long.parseLong(customerOrderData.get(1))),
                customerOrderData.get(2)
        );
    }

    @Override
    public List<String> toList() {
        return new ArrayList<>(List.of(
                String.valueOf(id),
                String.valueOf(customer.getId()),
                getStringOrderDateTime()
        ));
    }

    @Override
    public boolean fileAddNewRow() {
        List<String> customerOrderData = toList();
        return FileService.insertData(FILENAME, customerOrderData);
    }

    @Override
    public boolean fileUpdate() {
        return false;
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
