package data;

import model.Orders;

import java.util.List;

public class OrderDAO extends AbstractDAO<Orders> {


    public List<Orders> getAllUsers() {
        return super.findAll();
    }

    public int insert(Orders orders) {
        return super.insert(orders);
    }
}
