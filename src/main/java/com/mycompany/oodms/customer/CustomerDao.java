package com.mycompany.oodms.customer;

import com.mycompany.oodms.Dao.FileService;
import com.mycompany.oodms.OODMS;
import com.mycompany.oodms.Dao.ObjectDao;
import com.mycompany.oodms.user.UserDao;

import java.util.ArrayList;
import java.util.List;

public class CustomerDao implements ObjectDao<Customer> {
    public static final String FILENAME = "customer";

    private final UserDao userDao;

    public CustomerDao() {
        this.userDao = OODMS.getUserDao();
    }

    @Override
    public List<String> toList(Customer object) {
        return new ArrayList<>(List.of(
                String.valueOf(object.getId()),
                object.getUsername(),
                object.getPassword(),
                object.getEmail(),
                object.getPhoneNo(),
                String.valueOf(object.getStaff()),
                String.valueOf(object.getAdmin())
        ));
    }

    @Override
    public boolean fileAddNewRow(Customer user) {
        if (userDao.fileAddNewRow(user)) {
            List<String> customerData = List.of(
                    String.valueOf(user.getId())
            );
            return FileService.insertData(FILENAME, customerData);
        }
        return false;
    }

    @Override
    public boolean fileUpdate(Customer user) {
        if (userDao.fileUpdate(user)) {
            List<String> customerData = List.of(
                    String.valueOf(user.getId())
            );
            return FileService.updateSingleRow(FILENAME, customerData, FileService.ID_COLUMN);
        }
        return false;
    }
}
