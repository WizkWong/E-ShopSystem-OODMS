package com.mycompany.oodms.order;

public enum DeliveryStatus {
    UNASSIGNED("Unassigned"),
    PENDING("Pending"),
    DELIVERING("Delivering"),
    DELIVERED("Delivered"),
    CANCELLED("Cancelled");

    private final String status;

    DeliveryStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    // for customer to view the status
    public String getUserStatus() {
        return switch (this) {
            case UNASSIGNED, PENDING -> "Ordered";
            case DELIVERING -> "Delivering";
            case DELIVERED -> "Delivered";
            case CANCELLED -> "Cancelled";
        };
    }

    // get the status
    public static DeliveryStatus getStatus(String status) {
        return switch (status) {
            case "Unassigned" -> UNASSIGNED;
            case "Pending" -> PENDING;
            case "Delivering" -> DELIVERING;
            case "Delivered" -> DELIVERED;
            case "Cancelled" -> CANCELLED;
            default -> null;
        };
    }
}
