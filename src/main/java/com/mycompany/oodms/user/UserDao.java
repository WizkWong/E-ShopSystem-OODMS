package com.mycompany.oodms.user;

import com.mycompany.oodms.Dao.FileService;
import com.mycompany.oodms.Dao.ObjectDao;

import java.util.ArrayList;
import java.util.List;

public class UserDao implements ObjectDao<User> {
    // columns order in file: User ID, Username, Password, Email, Phone Number, IsStaff, IsAdmin
    public static final String FILENAME = "user";

    @Override
    public List<String> toList(User user) {
        return new ArrayList<>(List.of(
                String.valueOf(user.getId()),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getPhoneNo(),
                String.valueOf(user.getStaff()),
                String.valueOf(user.getAdmin())
        ));
    }

    @Override
    public boolean fileAddNewRow(User user) {
        List<String> userData = List.of(
                String.valueOf(user.getId()),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getPhoneNo(),
                String.valueOf(user.getStaff()),
                String.valueOf(user.getAdmin())
        );
        return FileService.insertData(FILENAME, userData);
    }

    @Override
    public boolean fileUpdate(User user) {
        List<String> userData = List.of(
                String.valueOf(user.getId()),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getPhoneNo(),
                String.valueOf(user.getStaff()),
                String.valueOf(user.getAdmin())
        );
        return FileService.updateSingleRow(FILENAME, userData, FileService.ID_COLUMN);
    }
}
