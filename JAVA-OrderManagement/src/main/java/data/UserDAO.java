package data;

import model.Users;

import java.util.List;

public class UserDAO extends AbstractDAO<Users> {

    public List<Users> getAllUsers() {
        return super.findAll();
    }

    public Users findByID(int idusers) {
        return super.findById(idusers);
    }

    public int insert(Users users) {
        return super.insert(users);
    }

    public boolean update(int idusers, Users users) {
        return super.update(idusers, users);
    }

    public boolean delete(int idusers) {
        return super.delete(idusers);
    }

}
