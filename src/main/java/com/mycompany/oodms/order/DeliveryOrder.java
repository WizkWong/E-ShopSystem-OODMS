package com.mycompany.oodms.order;

import com.mycompany.oodms.OODMS;
import com.mycompany.oodms.deliveryStaff.DeliveryStaff;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeliveryOrder {
    private static final DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    private CustomerOrder customerOrder;
    private DeliveryStaff deliveryStaff;
    private DeliveryStatus deliveryStatus;
    private String address;
    private LocalDateTime assignDateTime;
    private LocalDateTime deliveredDateTime;
    private String feedback;

    public DeliveryOrder(CustomerOrder customerOrder, String address, String feedback) {
        this.customerOrder = customerOrder;
        this.deliveryStaff = new DeliveryStaff();
        this.deliveryStatus = DeliveryStatus.UNASSIGNED;
        this.address = address;
        this.feedback = feedback;
    }

    public DeliveryOrder(CustomerOrder customerOrder, DeliveryStaff deliveryStaff, DeliveryStatus deliveryStatus, String address, LocalDateTime assignDateTime, LocalDateTime deliveredDateTime, String feedback) {
        this.customerOrder = customerOrder;
        this.deliveryStaff = deliveryStaff;
        this.deliveryStatus = deliveryStatus;
        this.address = address;
        this.assignDateTime = assignDateTime;
        this.deliveredDateTime = deliveredDateTime;
        this.feedback = feedback;
    }

    public DeliveryOrder(CustomerOrder customerOrder, String deliveryStaff, String deliveryStatus, String address, String assignDateTime, String deliveredDateTime, String feedback) {
        this(
                customerOrder,
                deliveryStaff.equals("null") ? null : OODMS.getDeliveryStaffDao().getById(Long.parseLong(deliveryStaff)),
                DeliveryStatus.getStatus(deliveryStatus),
                address,
                assignDateTime.equals("null") ? null : LocalDateTime.parse(assignDateTime, formatDateTime),
                deliveredDateTime.equals("null") ? null : LocalDateTime.parse(deliveredDateTime, formatDateTime),
                feedback.equals("null") ? null : feedback
        );
    }

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    public DeliveryStaff getDeliveryStaff() {
        return deliveryStaff;
    }

    public void setDeliveryStaff(DeliveryStaff deliveryStaff) {
        this.deliveryStaff = deliveryStaff;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getAssignDateTime() {
        return assignDateTime;
    }

    public String getStringAssignDateTime() {
        if (assignDateTime == null) {
            return null;
        }
        return assignDateTime.format(formatDateTime);
    }

    public void setAssignDateTime(LocalDateTime assignDateTime) {
        this.assignDateTime = assignDateTime;
    }

    public LocalDateTime getDeliveredDateTime() {
        return deliveredDateTime;
    }

    public String getStringDeliveredDateTime() {
        if (deliveredDateTime == null) {
            return null;
        }
        return deliveredDateTime.format(formatDateTime);
    }

    public void setDeliveredDateTime(LocalDateTime deliveredDateTime) {
        this.deliveredDateTime = deliveredDateTime;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
