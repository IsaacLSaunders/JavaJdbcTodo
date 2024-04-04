package com.jdbccrud.item;

import com.jdbccrud.exception.DatabaseAccessException;
import com.jdbccrud.exception.ResourceNotFoundException;
import com.jdbccrud.person.Person;
import com.jdbccrud.tag.Tag;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemDataAccessServiceHibernate implements IItemDAO{

    private final EntityManager entityManager;

    public ItemDataAccessServiceHibernate(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Item> getAllItems() {
            TypedQuery<Item> query = entityManager.createQuery("FROM Item ORDER BY createdDate", Item.class);
            return query.getResultList();
    }

    @Override
    public List<Item> getAllItemsByUsername(String username) {
            //use the named query from the person class so we dont have to keep writing out create query statements everywhere we need a person by username
            Person person = entityManager.createNamedQuery("Person.findByUsername", Person.class)
                    .setParameter("username", username)
                    .getSingleResult();

            if(person != null){
                return person.getItems();
            }

            return new ArrayList<>();
    }

    @Override
    public List<Item> getAllItemsByUserId(int userId) {

        Person person = entityManager.find(Person.class, userId);

        if(person == null){
            throw new EntityNotFoundException("Entity with userId " + userId + " not found.");
        }

        return person.getItems();

    }

    @Transactional
    @Override
    public Item addItemByUsername(Item newItem, String username) {
        try{
            //use the named query from the person class so we dont have to keep writing out create query statements everywhere we need a person by username
            Person person = entityManager.createNamedQuery("Person.findByUsername", Person.class)
                    .setParameter("username", username)
                    .getSingleResult();

            Tag tag = entityManager.find(Tag.class, newItem.getTagIdentity());

            if(person != null){
                newItem.setCreatedDate(LocalDateTime.now());
                newItem.setLastModifiedDate(newItem.getCreatedDate());
                newItem.setAsigneeId(person.getId());
                newItem.setTag(tag);

                entityManager.persist(newItem);

                return entityManager.find(Item.class, newItem.getId());
            }

            throw new ResourceNotFoundException("Resource: " + Tag.class + " || Not Found.");
        }
        catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException(e);
        }
        catch (Exception e){
            throw new DatabaseAccessException(e);
        }
    }

    @Transactional
    @Override
    public Item addItemByUserId(Item newItem, int id) {
        try{
            //use the named query from the person class so we dont have to keep writing out create query statements everywhere we need a person by username
            Person person = entityManager.find(Person.class, id);

            Tag tag = entityManager.find(Tag.class, newItem.getTagIdentity());

            if(person != null){
                newItem.setCreatedDate(LocalDateTime.now());
                newItem.setLastModifiedDate(newItem.getCreatedDate());
                newItem.setAsigneeId(person.getId());
                newItem.setTag(tag);

                entityManager.persist(newItem);

                return entityManager.find(Item.class, newItem.getId());
            }

            throw new ResourceNotFoundException("Resource: " + Tag.class + " || Not Found.");
        }
        catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException(e);
        }
        catch (Exception e){
            throw new DatabaseAccessException(e);
        }
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
