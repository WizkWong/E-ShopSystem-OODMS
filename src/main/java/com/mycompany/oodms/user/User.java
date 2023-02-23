package com.mycompany.oodms.user;

import com.mycompany.oodms.Dao.FileService;
import com.mycompany.oodms.admin.Admin;
import com.mycompany.oodms.admin.AdminDao;
import com.mycompany.oodms.customer.Customer;
import com.mycompany.oodms.deliveryStaff.DeliveryStaff;
import com.mycompany.oodms.deliveryStaff.DeliveryStaffDao;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public abstract class User {
    private static final Pattern VALID_EMAIL_ADDRESS = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private Long id;
    private String username;
    private String password;
    private String email;
    private String phoneNo;
    private Boolean staff;
    private Boolean admin;

    public User(Long id, String username, String password, String email, String phoneNo, Boolean staff, Boolean admin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNo = phoneNo;
        this.staff = staff;
        this.admin = admin;
    }

    public static User verify(String username, String password) {
        List<List<String>> allUser = FileService.readFile(UserDao.FILENAME);
        Optional<List<String>> userExist = allUser.stream().filter(list -> list.get(1).equals(username) && list.get(2).equals(password)).findFirst();
        // if no match
        if (userExist.isEmpty()) {
            return null;
        }
        List<String> user = userExist.get();

        // check is staff
        if (user.get(5).equals("true")) {
            return new DeliveryStaff(user);
        }
        
        // check is admin
        else if ((user.get(6).equals("true"))) {
            return new Admin(user);        
        }
        
        return new Customer(user);
    }

    // validate the input
    public static String validate(String name, String password1, String password2, String email, String phoneNo) {
        String errorMessage = "";

        if (name.length() < 4) {
            errorMessage += "Username character less than 4;";
        }

        if (password1.length() < 8) {
            errorMessage += "Password length less than 8;";

        } else if (!password1.equals(password2)) {
            errorMessage += "Password length is not same;";
        }

        if (!VALID_EMAIL_ADDRESS.matcher(email).find()) {
            errorMessage += "Email is invalid;";
        }

        if (phoneNo.length() < 10) {
            errorMessage += "Phone number is invalid;";
        }

        return errorMessage;
    }

    // validate the input
    public static String validate(String name, String email, String phoneNo) {
        String errorMessage = "";

        if (name.length() < 4) {
            errorMessage += "Username character less than 4;";
        }

        if (!VALID_EMAIL_ADDRESS.matcher(email).find()) {
            errorMessage += "Email is invalid;";
        }

        if (phoneNo.length() < 10) {
            errorMessage += "Phone number is invalid;";
        }

        return errorMessage;
    }

    // validate the input
    public static String validate(String password1, String password2) {
        String errorMessage = "";

        if (password1.length() < 8) {
            errorMessage += "Password length less than 8;";

        } else if (!password1.equals(password2)) {
            errorMessage += "Password length is not same;";
        }

        return errorMessage;
    }

    public static String checkUserExist(String name) {
        if (name.length() >= 4) {
            // get all user
            List<List<String>> allUser = FileService.readFile(UserDao.FILENAME);
            // find any username ignore case match
            boolean usernameTaken = allUser.stream().anyMatch(list -> list.get(1).equalsIgnoreCase(name));

            // check username taken
            if (usernameTaken) {
                return "Username taken;";
            }
        }
        return "";
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
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
                ", email='" + email + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", staff=" + staff +
                ", admin=" + admin +
                '}';
    }
}
