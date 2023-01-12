package com.mycompany.oodms.admin;

import com.mycompany.oodms.user.User;

import java.util.ArrayList;
import java.util.List;

public class Admin extends User {
    public static final String FILENAME = "admin";
    private Boolean superuser;

    public Admin(Long id, String username, String password, Boolean staff, Boolean admin, Boolean superuser) {
        super(id, username, password, staff, admin);
        this.superuser = superuser;
    }

    public Admin(List<String> adminData) {
        this(
                Long.valueOf(adminData.get(0)),
                adminData.get(1),
                adminData.get(2),
                Boolean.valueOf(adminData.get(3)),
                Boolean.valueOf(adminData.get(4)),
                Boolean.valueOf(adminData.get(5))
        );
    }

    public Admin() {
        this(null, null,null, null, null, null);
    }

    @Override
    public List<String> toList() {
        List<String> list = super.toList();
        list.add(String.valueOf(superuser));
        return list;
    }

    public Boolean getSuperuser() {
        return superuser;
    }

    public void setSuperuser(Boolean superuser) {
        this.superuser = superuser;
    }
}
