package com.mycompany.oodms.user;

import com.mycompany.oodms.FileService;
import com.mycompany.oodms.admin.Admin;
import com.mycompany.oodms.customer.Customer;
import com.mycompany.oodms.deliveryStaff.DeliveryStaff;

import java.util.ArrayList;
import java.util.List;

public abstract class User implements FileService {
    public static final String USER_FILENAME = "user";

    private Long id;
    private String username;
    private String password;
    private Boolean staff;
    private Boolean admin;

    public User(Long id, String username, String password, Boolean staff, Boolean admin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.staff = staff;
        this.admin = admin;
    }

    @Override
    public List<String> toList() {
        return new ArrayList<>(List.of(
                String.valueOf(id),
                username,
                password,
                String.valueOf(staff),
                String.valueOf(admin)
        ));
    }

    @Override
    public boolean fileAddNewRow() {
        /* reason not using toList() method because only need superclass of toList, but it uses subclass of toList
           while saving customer, staff or admin */
        List<String> userData = List.of(
                String.valueOf(id),
                username,
                password,
                String.valueOf(staff),
                String.valueOf(admin)
        );
        return FileService.insertData(USER_FILENAME, userData);
    }

    @Override
    public boolean fileUpdate() {
        List<String> userData = List.of(
                String.valueOf(id),
                username,
                password,
                String.valueOf(staff),
                String.valueOf(admin)
        );
        return FileService.updateSingleRow(USER_FILENAME, userData, FileService.ID_COLUMN);
    }

    public static User verify(String username, String password) {
        List<List<String>> allUser = FileService.readFile(USER_FILENAME);
        List<String> user = allUser.stream().filter(list -> list.get(1).equals(username) && list.get(2).equals(password)).toList().get(0);
        // if no match
        if (user.isEmpty()) {
            return null;
        }
        List<String> userSubClassData;
        // check is staff
        if (user.get(3).equals("1")) {
            userSubClassData = FileService.getOneSpecificData(DeliveryStaff.FILENAME, FileService.ID_COLUMN, user.get(0));
            List<String> staffData = joinWithUser(userSubClassData, user);
            if (staffData != null) {
                return new DeliveryStaff(staffData);
            }
        }
        // check is admin
        else if ((user.get(4).equals("1"))) {
            userSubClassData = FileService.getOneSpecificData(Admin.FILENAME, FileService.ID_COLUMN, user.get(0));
            List<String> adminData = joinWithUser(userSubClassData, user);
            if (adminData != null) {
                return new Admin(adminData);
            }
        }
        else {
            userSubClassData = FileService.getOneSpecificData(Customer.FILENAME, FileService.ID_COLUMN, user.get(0));
            List<String> customerData = joinWithUser(userSubClassData, user);
            if (customerData != null) {
                return new Customer(customerData);
            }
        }
        return null;
    }

    public static String validate(String name, String password) {
        String errorMessage = "";

        if (name.length() < 3) {
            errorMessage += "The total character of name must be more than or equal 4";
        }

        if (password.length() < 7) {
            errorMessage += "Minimum password length must be 8";
        }

        return errorMessage;
    }

    public static List<String> joinWithUser(List<String> subclassData, List<String> userData) {
        if (subclassData.get(0).equals(userData.get(0))) {
            subclassData.remove(0);
            userData.addAll(subclassData);
            return userData;
        }
        System.out.println("Id does not match, fail to join list");
        return null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStaff() {
        return staff;
    }

    public void setStaff(Boolean staff) {
        this.staff = staff;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", staff=" + staff +
                ", admin=" + admin +
                '}';
    }
}
