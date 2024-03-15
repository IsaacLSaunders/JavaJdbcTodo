package com.jdbccrud.item;

import com.jdbccrud.person.PersonController;
import com.jdbccrud.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/item")
public class ItemController{

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    @GetMapping("/all")
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/username/{username}")
    public List<Item> getAllItemsByUsername(@PathVariable String username) {
        return itemService.getAllItemsByUsername(username);
    }

    @GetMapping("/userId/{userId}")
    public List<Item> getAllItemsByUserId(@PathVariable int userId) {
        return itemService.getAllItemsByUserId(userId);
    }

    @PostMapping("/username/{username}")
    public Item addItemByUsername(@RequestBody Item newItem, @PathVariable String username) {
        return itemService.addItemByUsername(newItem, username);

        //Take a look at the ResponseEntity class to return more detailed and specific Http status codes
//        return new ResponseEntity<>(brewery, HttpStatus.CREATED)
    }

    @PostMapping("/userId/{userId}")
    public Item addItemByUserId(@RequestBody Item newItem, @PathVariable int userId) {
        return itemService.addItemByUserId(newItem, userId);
    }

    @PutMapping("/itemId/{itemId}")
    public Item editItemByItemId(@RequestBody Item editedItem, @PathVariable int itemId) {
        return itemService.editItemByItemId(editedItem, itemId);
    }

    @DeleteMapping("itemId/{itemId}")
    public int deleteItemByItemId(@PathVariable int itemId) {
        return itemService.deleteItemByItemId(itemId);
    }

    @DeleteMapping("personId/{personId}")
    public int deleteAllItemsByPersonId(@PathVariable int personId) {
        return itemService.deleteAllItemsByPersonId(personId);
    }
}
