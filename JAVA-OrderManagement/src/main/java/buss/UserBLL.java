package buss;

import data.UserDAO;
import model.Users;
import vaildate.EmailValidator;
import vaildate.PhoneValidator;
import vaildate.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class UserBLL {
    private UserDAO userDAO;
    private List<Validator<Users>> vali;

    public UserBLL() {
        vali = new ArrayList<Validator<Users>>();
        vali.add(new EmailValidator());
        vali.add(new PhoneValidator());

        userDAO = new UserDAO();
    }

    public List<Users> getAllUsers() {
        List<Users> myUsers = userDAO.getAllUsers();
        if (myUsers == null) {
            throw new NoSuchElementException("Not 1 found");
        }
        return myUsers;
    }

    public Users findUserByID(int usersID) {
        Users newUser = userDAO.findByID(usersID);
        if (newUser == null) {
            throw new NoSuchElementException("Noone found");
        }
        return newUser;
    }

    public Users insertNewUser(Users newUser) {
        newUser.setUserID(userDAO.insert(newUser));
        return newUser;
    }

    public boolean deleteUser(int newUserID) {
        boolean deleteDone = userDAO.delete(newUserID);
        return deleteDone;
    }

    public boolean updateUser(int newUserID, Users newUser) {
        boolean updateDone = userDAO.update(newUserID, newUser);
        return updateDone;
    }
}
