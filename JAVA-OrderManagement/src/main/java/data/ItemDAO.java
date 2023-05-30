package data;

import model.Items;

import java.util.List;

/**
 * The ItemDAO class provides data access methods for interacting with the Items table in the database.
 * It extends the AbstractDAO class, inheriting common database operations.
 */
public class ItemDAO extends AbstractDAO<Items> {
    /**
     * Retrieves all items from the database.
     *
     * @return a List of Items containing all items in the database
     */
    public List<Items> getAllItems() {
        return super.findAll();
    }
    /**
     * Retrieves an item from the database based on the specified item ID.
     *
     * @param idItem the ID of the item to retrieve
     * @return the Items object representing the item with the specified ID, or null if not found
     */
    public Items findByID(int idItem) {
        return super.findById(idItem);
    }
    /**
     * Inserts a new item into the database.
     *
     * @param items the Items object representing the item to insert
     * @return the generated ID of the inserted item
     */
    public int insert(Items items) {
        return super.insert(items);
    }
    /**
     * Updates an existing item in the database.
     *
     * @param itemId the ID of the item to update
     * @param items  the Items object representing the updated item
     * @return true if the update was successful, false otherwise
     */
    public boolean update(int itemId, Items items) {
        return super.update(itemId, items);
    }
    /**
     * Deletes an item from the database based on the specified item ID.
     *
     * @param itemsID the ID of the item to delete
     * @return true if the item was successfully deleted, false otherwise
     */
    public boolean delete(int itemsID) {
        return super.delete(itemsID);
    }
}
