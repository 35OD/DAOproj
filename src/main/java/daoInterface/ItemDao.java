package daoInterface;

import model.Item;

import java.util.List;

public interface ItemDao {
    List<Item> getAllItems();
    void addItem(List<Item> itemList);
    boolean removeItemById(int id);

}
