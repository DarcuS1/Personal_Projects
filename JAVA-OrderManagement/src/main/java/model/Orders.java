package model;

public class Orders {
    private int orderID;
    private int userID;
    private int itemID;

    private int quantity;

    public Orders(int orderID, int userID, int itemID, int quantity) {
        this.orderID = orderID;
        this.userID = userID;
        this.itemID = itemID;
        this.quantity = quantity;
    }

    public Orders() {

    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }
}
