package com.mycompany.oodms.admin;

import com.mycompany.oodms.user.User;

import java.util.ArrayList;
import java.util.List;

public class Admin extends User {
    private Boolean superuser;

    public Admin(Long id, String username, String password, Boolean staff, Boolean admin, Boolean superuser) {
        super(id, username, password, staff, admin);
        this.superuser = superuser;
    }

    public Admin() {
        this(null, null,null, null, null, null);
    }

    public List<String> toList() {
        return new ArrayList<>(List.of(
                String.valueOf(this.getId()),
                this.getUsername(),
                this.getPassword(),
                String.valueOf(this.getStaff()),
                String.valueOf(this.getAdmin()),
                String.valueOf(this.getSuperuser())
                ));
    }

    public Boolean getSuperuser() {
        return superuser;
    }

    public void setSuperuser(Boolean superuser) {
        this.superuser = superuser;
    }
}
