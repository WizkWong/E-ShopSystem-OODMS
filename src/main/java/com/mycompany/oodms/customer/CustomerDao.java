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
    public List<String> toList(Customer customer) {
        return new ArrayList<>(List.of(
                String.valueOf(customer.getId()),
                customer.getUsername(),
                customer.getPassword(),
                customer.getEmail(),
                customer.getPhoneNo(),
                String.valueOf(customer.getStaff()),
                String.valueOf(customer.getAdmin())
        ));
    }

    @Override
    public boolean fileAddNewRow(Customer customer) {
        if (userDao.fileAddNewRow(customer)) {
            List<String> customerData = List.of(
                    String.valueOf(customer.getId())
            );
            return FileService.insertData(FILENAME, customerData);
        }
        return false;
    }

    @Override
    public boolean fileUpdate(Customer customer) {
        if (userDao.fileUpdate(customer)) {
            List<String> customerData = List.of(
                    String.valueOf(customer.getId())
            );
            return FileService.updateSingleRow(FILENAME, customerData, FileService.ID_COLUMN);
        }
        return false;
    }
}
