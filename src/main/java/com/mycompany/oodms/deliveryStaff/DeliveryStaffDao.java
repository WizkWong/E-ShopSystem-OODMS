package com.mycompany.oodms.deliveryStaff;

import com.mycompany.oodms.Dao.FileService;
import com.mycompany.oodms.user.UserDao;

import java.util.List;

public class DeliveryStaffDao extends UserDao<DeliveryStaff> {

    @Override
    public List<DeliveryStaff> getAll() {
        List<List<String>> array = FileService.readFile(FILENAME);
        return array.stream().filter(list -> list.get(5).equals("true")).map(DeliveryStaff::new).toList();
    }

    @Override
    public DeliveryStaff getById(long id) {
        List<String> userData = FileService.getOneSpecificData(FILENAME, FileService.ID_COLUMN, String.valueOf(id));
        if (!userData.isEmpty() && userData.get(5).equals("true")) {
            return new DeliveryStaff(userData);
        }
        return null;
    }

    public boolean remove(DeliveryStaff deliveryStaff) {
        return FileService.removeById(FILENAME, List.of(toList(deliveryStaff)));
    }
}
