package com.jdbccrud.item;

import java.util.List;

public interface IItemDAO {

    public List<Item> getAllItems();

    public List<Item> getAllItemsByUsername(String username);

    public List<Item> getAllItemsByUserId(int userId);

    public Item addItemByUsername(Item newItem, String username);

    public Item addItemByUserId(Item newItem, int id);

    public Item editItemByItemId(Item editedItem, int id);

    public int deleteItemByItemId(int itemId);

    public int deleteAllItemsByPersonId(int personId);


}
