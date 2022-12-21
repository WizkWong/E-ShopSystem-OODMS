package com.mycompany.oodms.deliveryStaff;

import com.mycompany.oodms.user.User;

public class DeliveryStaff extends User {

    private String phoneNo;

    public DeliveryStaff(Long id, String username, String password, Boolean staff, Boolean admin, String phoneNo) {
        super(id, username, password, staff, admin);
        this.phoneNo = phoneNo;
    }

    public DeliveryStaff() {
        this(null, null,null, null, null, null);
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
