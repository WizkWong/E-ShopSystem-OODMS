package com.mycompany.oodms.order;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class CustomerOrderPayment {
    private static final DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    private CustomerOrder customerOrder;
    private String typeOfPayment;
    private String cardNumber;
    private Double totalPrice;
    private LocalDateTime paymentDateTime;

    public CustomerOrderPayment(CustomerOrder customerOrder, String typeOfPayment, String cardNumber, Double totalPrice) {
        this.customerOrder = customerOrder;
        this.typeOfPayment = typeOfPayment;
        this.cardNumber = cardNumber;
        this.totalPrice = totalPrice;
        this.paymentDateTime = LocalDateTime.now();
    }

    public CustomerOrderPayment(CustomerOrder customerOrder, String typeOfPayment, String cardNumber, Double totalPrice, String paymentDateTime) {
        this.customerOrder = customerOrder;
        this.typeOfPayment = typeOfPayment;
        this.cardNumber = cardNumber;
        this.totalPrice = totalPrice;
        this.paymentDateTime = LocalDateTime.parse(paymentDateTime, formatDateTime);
    }

    public static String validate(String card, String monthString, String yearString, String cvc) {
        String errorMsg = "";

        if (card.isEmpty()) {
            errorMsg += "Card is empty;";
        }
        if (monthString.isEmpty()) {
            errorMsg += "Month is empty;";
        }
        if (yearString.isEmpty()) {
            errorMsg += "Year is empty;";
        }
        if (cvc.isEmpty()) {
            errorMsg += "cvc is empty;";

        } else if (cvc.length() != 3) {
            errorMsg += "cvc is invalid;";
        }

        if (!monthString.isEmpty() && !yearString.isEmpty()) {
            int month = Integer.parseInt(monthString);
            int year = Integer.parseInt(yearString);

            if (month < 1 || month > 12) {
                errorMsg += "expiry date invalid;";

            } else if (YearMonth.now().isAfter(YearMonth.from(LocalDate.of(year, month, 1)))) {
                errorMsg += "this card is expired;";
            }
        }
        return errorMsg;
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

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
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
