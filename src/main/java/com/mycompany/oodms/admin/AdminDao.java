package com.mycompany.oodms.admin;

import com.mycompany.oodms.Dao.FileService;
import com.mycompany.oodms.user.UserDao;

import java.util.List;

public class AdminDao extends UserDao<Admin> {

    @Override
    public List<Admin> getAll() {
        List<List<String>> array = FileService.readFile(FILENAME);
        return array.stream().filter(list -> list.get(6).equals("true")).map(Admin::new).toList();
    }

    @Override
    public Admin getById(long id) {
        List<String> userData = FileService.getOneSpecificData(FILENAME, FileService.ID_COLUMN, String.valueOf(id));
        if (!userData.isEmpty() && userData.get(6).equals("true")) {
            return new Admin(userData);
        }
        return null;
    }

    public boolean remove(Admin admin) {
        return FileService.removeById(FILENAME, List.of(toList(admin)));
    }
}
