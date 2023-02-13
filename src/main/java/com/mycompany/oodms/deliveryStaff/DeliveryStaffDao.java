package com.mycompany.oodms.deliveryStaff;

import com.mycompany.oodms.Dao.FileService;
import com.mycompany.oodms.OODMS;
import com.mycompany.oodms.Dao.ObjectDao;
import com.mycompany.oodms.user.UserDao;

import java.util.ArrayList;
import java.util.List;

public class DeliveryStaffDao implements ObjectDao<DeliveryStaff> {
    public static final String FILENAME = "delivery staff";

    private final UserDao userDao;

    public DeliveryStaffDao() {
        this.userDao = OODMS.getUserDao();
    }

    @Override
    public List<String> toList(DeliveryStaff deliveryStaff) {
        return new ArrayList<>(List.of(
                String.valueOf(deliveryStaff.getId()),
                deliveryStaff.getUsername(),
                deliveryStaff.getPassword(),
                deliveryStaff.getEmail(),
                deliveryStaff.getPhoneNo(),
                String.valueOf(deliveryStaff.getStaff()),
                String.valueOf(deliveryStaff.getAdmin())
        ));
    }

    @Override
    public boolean fileAddNewRow(DeliveryStaff deliveryStaff) {
        if (userDao.fileAddNewRow(deliveryStaff)) {
            List<String> customerData = List.of(
                    String.valueOf(deliveryStaff.getId())
            );
            return FileService.insertData(FILENAME, customerData);
        }
        return false;
    }

    @Override
    public boolean fileUpdate(DeliveryStaff deliveryStaff) {
        if (userDao.fileUpdate(deliveryStaff)) {
            List<String> customerData = List.of(
                    String.valueOf(deliveryStaff.getId())
            );
            return FileService.updateSingleRow(FILENAME, customerData, FileService.ID_COLUMN);
        }
        return false;
    }
}
