package com.mycompany.oodms.admin;

import com.mycompany.oodms.Dao.FileService;
import com.mycompany.oodms.OODMS;
import com.mycompany.oodms.Dao.ObjectDao;
import com.mycompany.oodms.user.UserDao;

import java.util.ArrayList;
import java.util.List;

public class AdminDao implements ObjectDao<Admin> {
    public static final String FILENAME = "admin";

    private final UserDao userDao;

    public AdminDao() {
        this.userDao = OODMS.getUserDao();
    }

    @Override
    public List<String> toList(Admin admin) {
        return new ArrayList<>(List.of(
                String.valueOf(admin.getId()),
                admin.getUsername(),
                admin.getPassword(),
                admin.getEmail(),
                admin.getPhoneNo(),
                String.valueOf(admin.getStaff()),
                String.valueOf(admin.getAdmin())
        ));
    }

    @Override
    public boolean fileAddNewRow(Admin admin) {
        if (userDao.fileAddNewRow(admin)) {
            List<String> customerData = List.of(
                    String.valueOf(admin.getId())
            );
            return FileService.insertData(FILENAME, customerData);
        }
        return false;
    }

    @Override
    public boolean fileUpdate(Admin admin) {
        if (userDao.fileUpdate(admin)) {
            List<String> customerData = List.of(
                    String.valueOf(admin.getId())
            );
            return FileService.updateSingleRow(FILENAME, customerData, FileService.ID_COLUMN);
        }
        return false;
    }
}