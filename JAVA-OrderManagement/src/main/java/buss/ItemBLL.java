package buss;

import data.ItemDAO;
import model.Items;

import java.util.ArrayList;
import java.util.List;

public class ItemBLL {

    private ItemDAO newItemDAO;

    public ItemBLL() {
        newItemDAO = new ItemDAO();
    }

    public List<Items> getAllItems() {
        List<Items> myItems = newItemDAO.getAllItems();
        return myItems;
    }

    public Items findItemsByID(int idItems) {
        Items myItems = newItemDAO.findByID(idItems);
        return myItems;
    }

    public Items insertNewItems(Items newItem) {
        newItem.setItemID(newItemDAO.insert(newItem));
        return newItem;
    }

    public boolean deleteItem(int newIdItem) {
        boolean deleteDone = newItemDAO.delete(newIdItem);
        return deleteDone;
    }

    public boolean updateNewItem(int newIdItem, Items newItems) {
        boolean updateDone = newItemDAO.update(newIdItem, newItems);
        return updateDone;
    }
}
