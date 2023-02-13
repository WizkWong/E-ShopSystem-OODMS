package com.mycompany.oodms.order;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomerOrderPayment {
    private static final DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    private CustomerOrder customerOrder;
    private String typeOfPayment;
    private Double totalPrice;
    private LocalDateTime paymentDateTime;

    public CustomerOrderPayment(CustomerOrder customerOrder, String typeOfPayment, Double totalPrice) {
        this.customerOrder = customerOrder;
        this.typeOfPayment = typeOfPayment;
        this.totalPrice = totalPrice;
        this.paymentDateTime = LocalDateTime.now();
    }

    public CustomerOrderPayment(CustomerOrder customerOrder, String typeOfPayment, Double totalPrice, String paymentDateTime) {
        this.customerOrder = customerOrder;
        this.typeOfPayment = typeOfPayment;
        this.totalPrice = totalPrice;
        this.paymentDateTime = LocalDateTime.parse(paymentDateTime, formatDateTime);
    }

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    public String getTypeOfPayment() {
        return typeOfPayment;
    }

    public void setTypeOfPayment(String typeOfPayment) {
        this.typeOfPayment = typeOfPayment;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getPaymentDateTime() {
        return paymentDateTime;
    }

    public String getStringPaymentDateTime() {
        return paymentDateTime.format(formatDateTime);
    }

    public void setPaymentDateTime(LocalDateTime paymentDateTime) {
        this.paymentDateTime = paymentDateTime;
    }
}
