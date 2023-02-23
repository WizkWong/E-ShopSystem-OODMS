package com.mycompany.oodms.order;

public enum DeliveryStatus {
    UNASSIGNED("Unassigned"),
    PENDING("Pending"),
    DELIVERING("Delivering"),
    DELIVERED("Delivered");

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
        };
    }

    // get the status
    public static DeliveryStatus getStatus(String status) {
        return switch (status) {
            case "Unassigned" -> UNASSIGNED;
            case "Pending" -> PENDING;
            case "Delivering" -> DELIVERING;
            case "Delivered" -> DELIVERED;
            default -> null;
        };
    }
}
