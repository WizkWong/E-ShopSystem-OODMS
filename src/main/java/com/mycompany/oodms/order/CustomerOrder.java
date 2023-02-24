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
    private DeliveryOrder deliveryOrder;

    public CustomerOrder(Long id, String typeOfPayment, String cardNumber, Customer customer, String address) {
        this.id = id;
        this.customer = customer;
        this.orderDateTime = LocalDateTime.now();
        this.orderDetail = customer.getCart().stream().map(CartItem::convertToOrderDetail).toList();
        this.customerOrderPayment = new CustomerOrderPayment(this, typeOfPayment, cardNumber, OrderDetail.calculateTotalPrice(this.orderDetail));
        this.deliveryOrder = new DeliveryOrder(this, address);
    }

    public CustomerOrder(Long id, Customer customer, LocalDateTime orderDateTime) {
        this.id = id;
        this.customer = customer;
        this.orderDateTime = orderDateTime;
        this.orderDetail = OODMS.getOrderDetailDao().getById(id);
        this.customerOrderPayment = OODMS.getCustomerOrderPaymentDao().getByCustomerOrderId(this);
        this.deliveryOrder = OODMS.getDeliveryOrderDao().getByCustomerOrderId(this);
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

    public static String validate(String unitNo, String street, String city, String postalCode, String state) {
        String errorMsg = "";

        if (unitNo.isEmpty()) {
            errorMsg += "Unit No is empty;";
        }
        if (street.isEmpty()) {
            errorMsg += "Street is empty;";
        }
        if (city.isEmpty()) {
            errorMsg += "City is empty;";
        }
        if (postalCode.isEmpty()) {
            errorMsg += "Postal Code is empty;";
        }
        if (state.isEmpty()) {
            errorMsg += "State is empty;";
        }

        return errorMsg;
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

    public DeliveryOrder getDeliveryOrder() {
        return deliveryOrder;
    }

    public void setDeliveryOrder(DeliveryOrder deliveryOrder) {
        this.deliveryOrder = deliveryOrder;
    }
}
