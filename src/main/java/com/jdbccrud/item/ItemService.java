package com.jdbccrud.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService{

    private final ItemDataAccessServiceHibernate itemDataAccessServiceHibernate;

    @Autowired
    public ItemService(ItemDataAccessServiceHibernate itemDataAccessServiceHibernate) {
        this.itemDataAccessServiceHibernate = itemDataAccessServiceHibernate;
    }

    public List<Item> getAllItems() {
        return itemDataAccessServiceHibernate.getAllItems();
    }

    public List<Item> getAllItemsByUsername(String username) {
        return itemDataAccessServiceHibernate.getAllItemsByUsername(username);
    }

    public List<Item> getAllItemsByUserId(int userId) {
        return itemDataAccessServiceHibernate.getAllItemsByUserId(userId);
    }

    public Item addItemByUsername(Item newItem, String username) {
        return itemDataAccessServiceHibernate.addItemByUsername(newItem, username);
    }

    public Item addItemByUserId(Item newItem, int id) {
        return itemDataAccessServiceHibernate.addItemByUserId(newItem, id);
    }

    public Item editItemByItemId(Item editedItem, int itemId) {
        return itemDataAccessServiceHibernate.editItemByItemId(editedItem, itemId);
    }

    public int deleteItemByItemId(int itemId) {
        return itemDataAccessServiceHibernate.deleteItemByItemId(itemId);
    }

    public int deleteAllItemsByPersonId(int personId) {
        return itemDataAccessServiceHibernate.deleteAllItemsByPersonId(personId);
    }
}
