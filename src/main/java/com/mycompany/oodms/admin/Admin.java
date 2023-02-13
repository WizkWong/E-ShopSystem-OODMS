package com.mycompany.oodms.admin;

import com.mycompany.oodms.user.User;

import java.util.List;

public class Admin extends User {

    public Admin(Long id, String username, String password, String email, String phoneNo, Boolean staff, Boolean admin) {
        super(id, username, password, email, phoneNo, staff, admin);
    }

    public Admin(List<String> adminData) {
        this(
                Long.valueOf(adminData.get(0)),
                adminData.get(1),
                adminData.get(2),
                adminData.get(3),
                adminData.get(4),
                Boolean.valueOf(adminData.get(5)),
                Boolean.valueOf(adminData.get(6))
        );
    }

    public Admin() {
        this(null, null,null, null, null, null, null);
    }
}
