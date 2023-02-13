package com.mycompany.oodms.deliveryStaff;

import com.mycompany.oodms.user.User;

import java.util.List;

public class DeliveryStaff extends User {

    public DeliveryStaff(Long id, String username, String password, String email, String phoneNo, Boolean staff, Boolean admin) {
        super(id, username, password, email, phoneNo, staff, admin);
    }

    public DeliveryStaff(List<String> deliveryStaffData) {
        this(
                Long.valueOf(deliveryStaffData.get(0)),
                deliveryStaffData.get(1),
                deliveryStaffData.get(2),
                deliveryStaffData.get(3),
                deliveryStaffData.get(4),
                Boolean.valueOf(deliveryStaffData.get(5)),
                Boolean.valueOf(deliveryStaffData.get(6))
        );
    }

    public DeliveryStaff() {
        this(null, null, null, null, null, null, null);
    }
}
