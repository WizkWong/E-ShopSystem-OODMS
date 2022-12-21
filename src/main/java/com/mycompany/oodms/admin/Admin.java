package com.mycompany.oodms.admin;

import com.mycompany.oodms.user.User;

public class Admin extends User {

    public Admin(Long id, String username, String password, Boolean staff, Boolean admin) {
        super(id, username, password, staff, admin);
    }

    public Admin() {
        this(null, null,null, null, null);
    }
}
