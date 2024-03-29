//package com.jdbccrud.item;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ItemService{
//
//    private final ItemDataAccessServiceJDBC itemDataAccessServiceJDBC;
//
//    @Autowired
//    public ItemService(ItemDataAccessServiceJDBC itemDataAccessServiceJDBC) {
//        this.itemDataAccessServiceJDBC = itemDataAccessServiceJDBC;
//    }
//
//    public List<Item> getAllItems() {
//        return itemDataAccessServiceJDBC.getAllItems();
//    }
//
//    public List<Item> getAllItemsByUsername(String username) {
//        return itemDataAccessServiceJDBC.getAllItemsByUsername(username);
//    }
//
//    public List<Item> getAllItemsByUserId(int userId) {
//        return itemDataAccessServiceJDBC.getAllItemsByUserId(userId);
//    }
//
//    public Item addItemByUsername(Item newItem, String username) {
//        return itemDataAccessServiceJDBC.addItemByUsername(newItem, username);
//    }
//
//    public Item addItemByUserId(Item newItem, int id) {
//        return itemDataAccessServiceJDBC.addItemByUserId(newItem, id);
//    }
//
//    public Item editItemByItemId(Item editedItem, int itemId) {
//        return itemDataAccessServiceJDBC.editItemByItemId(editedItem, itemId);
//    }
//
//    public int deleteItemByItemId(int itemId) {
//        return itemDataAccessServiceJDBC.deleteItemByItemId(itemId);
//    }
//
//    public int deleteAllItemsByPersonId(int personId) {
//        return itemDataAccessServiceJDBC.deleteAllItemsByPersonId(personId);
//    }
//}
