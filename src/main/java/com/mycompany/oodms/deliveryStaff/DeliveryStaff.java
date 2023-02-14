package com.mycompany.oodms.deliveryStaff;

import com.mycompany.oodms.Dao.FileService;
import com.mycompany.oodms.OODMS;
import com.mycompany.oodms.user.User;
import com.mycompany.oodms.user.UserDao;

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

        DeliveryStaff deliveryStaff = new DeliveryStaff(id, name, password1, email, phoneNo, true, false);
        // save new customer data
        OODMS.getDeliveryStaffDao().fileAddNewRow(deliveryStaff);

        return "";
    }
}
