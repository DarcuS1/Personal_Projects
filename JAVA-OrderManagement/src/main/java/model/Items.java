package model;

public class Items {


    private int itemID;
    private String itemName;
    private double itemPrice;
    private int itemStock;

    public Items(int itemID, String itemName, double itemPrice, int itemStock) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemStock = itemStock;
    }

    public Items() {
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemStock() {
        return itemStock;
    }

    public void setItemStock(int itemStock) {
        this.itemStock = itemStock;
    }
}
