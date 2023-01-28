package com.mycompany.oodms.order;

import com.mycompany.oodms.FileService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CustomerOrderPayment implements FileService {
    // columns order in file: CustomerOrder-ID, type-of-payment, total-price, payment-datetime

    public static final String FILENAME = "customer order payment";
    private final DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private CustomerOrder customerOrder;
    private String typeOfPayment;
    private Double totalPrice;
    private LocalDateTime paymentDateTime;

    public CustomerOrderPayment(CustomerOrder customerOrder, String typeOfPayment, Double totalPrice, LocalDateTime paymentDateTime) {
        this.customerOrder = customerOrder;
        this.typeOfPayment = typeOfPayment;
        this.totalPrice = totalPrice;
        this.paymentDateTime = paymentDateTime;
    }

    public CustomerOrderPayment(CustomerOrder customerOrder, String typeOfPayment, Double totalPrice, String paymentDateTime) {
        this.customerOrder = customerOrder;
        this.typeOfPayment = typeOfPayment;
        this.totalPrice = totalPrice;
        this.paymentDateTime = LocalDateTime.parse(paymentDateTime, formatDateTime);
    }

    @Override
    public List<String> toList() {
        return new ArrayList<>(List.of(
                String.valueOf(customerOrder.getId()),
                typeOfPayment,
                String.valueOf(totalPrice),
                getStringPaymentDateTime()
        ));
    }

    @Override
    public boolean fileAddNewRow() {
        List<String> customerOrderPaymentData = toList();
        return FileService.insertData(FILENAME, customerOrderPaymentData);
    }

    @Override
    public boolean fileUpdate() {
        return false;
    }

    public static CustomerOrderPayment getCustomerOrderPaymentById(CustomerOrder customerOrder) {
        Long orderId = customerOrder.getId();
        List<String> customerOrderPaymentData = FileService.getOneSpecificData(FILENAME, FileService.ID_COLUMN, String.valueOf(orderId));
        return new CustomerOrderPayment(
                customerOrder,
                customerOrderPaymentData.get(1),
                Double.valueOf(customerOrderPaymentData.get(2)),
                customerOrderPaymentData.get(3)
        );
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
