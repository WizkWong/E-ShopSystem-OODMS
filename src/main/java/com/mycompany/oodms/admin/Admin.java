package com.mycompany.oodms.admin;

import com.mycompany.oodms.Dao.FileService;
import com.mycompany.oodms.OODMS;
import com.mycompany.oodms.user.User;
import com.mycompany.oodms.user.UserDao;

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


    // create a new account
    public static String createAcc(String name, String password1, String password2, String email, String phoneNo) {
        // validate the name, password and phone number
        String errorMessage = User.validate(name, password1, password2, email, phoneNo) + User.checkUserExist(name);

        // if error message is not empty then return error message
        if (!errorMessage.isEmpty()) {
            return errorMessage;
        }

        // get new id
        Long id = FileService.getNewId(UserDao.FILENAME);
        if (id == null || id == -1) {
            return "System Error";
        }

        Admin admin = new Admin(id, name, password1, email, phoneNo, false, true);
        // save new customer data
        OODMS.getAdminDao().fileAddNewRow(admin);

        return "";
    }
}
