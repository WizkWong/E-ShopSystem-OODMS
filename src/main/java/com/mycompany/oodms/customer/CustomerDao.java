package com.mycompany.oodms.customer;

import com.mycompany.oodms.Dao.FileService;
import com.mycompany.oodms.user.UserDao;

import java.util.List;

public class CustomerDao extends UserDao<Customer> {

    @Override
    public List<Customer> getAll() {
        List<List<String>> array = FileService.readFile(FILENAME);
        return array.stream().filter(list -> list.get(5).equals("false") && list.get(6).equals("false")).map(Customer::new).toList();
    }

    // get the customer data by customer id
    @Override
    public Customer getById(long id) {
        String idString = String.valueOf(id);
        List<String> userData = FileService.getOneSpecificData(FILENAME, FileService.ID_COLUMN, idString);
        if (userData.isEmpty()) {
            return null;
        }
        return new Customer(userData);
    }
}
