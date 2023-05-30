package buss;

import data.ItemDAO;
import data.OrderDAO;
import model.Items;
import model.Orders;

import java.util.ArrayList;

public class OrderBLL {

    private OrderDAO orderDAO;

    public OrderBLL() {
        orderDAO = new OrderDAO();
    }

    public Orders insertNewOrder(Orders newOrder) {
        newOrder.setOrderID(orderDAO.insert(newOrder));
        return newOrder;
    }

    public ArrayList<Orders> getAllOrders() {
        return (ArrayList<Orders>) orderDAO.getAllUsers();
    }

    public void updateOrder(int orderID, Orders order) {
        orderDAO.update(orderID, order);
    }

    public void deleteOrder(int orderID) {
        orderDAO.delete(orderID);
    }


}
