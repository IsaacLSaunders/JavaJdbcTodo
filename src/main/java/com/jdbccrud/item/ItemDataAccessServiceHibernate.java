package com.jdbccrud.item;

import java.util.List;

public class ItemDataAccessServiceHibernate implements IItemDAO{
    @Override
    public List<Item> getAllItems() {
        return null;
    }

    @Override
    public List<Item> getAllItemsByUsername(String username) {
        return null;
    }

    @Override
    public List<Item> getAllItemsByUserId(int userId) {
        return null;
    }

    @Override
    public Item addItemByUsername(Item newItem, String username) {
        return null;
    }

    @Override
    public Item addItemByUserId(Item newItem, int id) {
        return null;
    }

    @Override
    public Item editItemByItemId(Item editedItem, int id) {
        return null;
    }

    @Override
    public int deleteItemByItemId(int itemId) {
        return 0;
    }

    @Override
    public int deleteAllItemsByPersonId(int personId) {
        return 0;
    }
}
