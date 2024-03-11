package com.jdbccrud.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService{

    private final ItemDataAccessService itemDataAccessService;

    @Autowired
    public ItemService(ItemDataAccessService itemDataAccessService) {
        this.itemDataAccessService = itemDataAccessService;
    }

    public List<Item> getAllItems() {
        return itemDataAccessService.getAllItems();
    }

    public List<Item> getAllItemsByUsername(String username) {
        return itemDataAccessService.getAllItemsByUsername(username);
    }

    public List<Item> getAllItemsByUserId(int userId) {
        return itemDataAccessService.getAllItemsByUserId(userId);
    }

    public Item addItemByUsername(Item newItem, String username) {
        return itemDataAccessService.addItemByUsername(newItem, username);
    }

    public Item addItemByUserId(Item newItem, int id) {
        return itemDataAccessService.addItemByUserId(newItem, id);
    }

    public Item editItemByItemId(Item editedItem, int id) {
        return itemDataAccessService.editItemByItemId(editedItem, id);
    }

    public int deleteItemByItemId(int itemId) {
        return itemDataAccessService.deleteItemByItemId(itemId);
    }

    public int deleteAllItemsByPersonId(int personId) {
        return itemDataAccessService.deleteAllItemsByPersonId(personId);
    }
}
