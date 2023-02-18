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
