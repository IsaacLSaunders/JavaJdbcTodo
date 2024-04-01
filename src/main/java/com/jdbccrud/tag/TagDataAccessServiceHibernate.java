package com.jdbccrud.tag;

import com.jdbccrud.exception.DatabaseAccessException;
import com.jdbccrud.exception.GeneralCatchAllException;
import com.jdbccrud.exception.ResourceNotFoundException;
import com.jdbccrud.item.Item;
import com.jdbccrud.item_tag.ItemTagDTO;
import com.jdbccrud.person.Person;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class TagDataAccessServiceHibernate implements ITagDAO{

    private final EntityManager entityManager;

    @Autowired
    public TagDataAccessServiceHibernate (EntityManager entityManager){
        this.entityManager = entityManager;
    }


    @Override
    public List<Tag> getAllTags() {
        try{
            TypedQuery<Tag> query = entityManager.createQuery("FROM Tag ORDER BY name", Tag.class);
            return query.getResultList();
        } catch (Exception e){
            throw new DatabaseAccessException("Error accessing the database || " + e.getMessage(), e);
        }    }

    @Override
    public Tag getTagById(int tagId) {
        try {
            Tag tag = entityManager.find(Tag.class, tagId);
            if(tag != null){
                return tag;
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
    public Tag getTagByItemId(int itemId) {
        try{
            Item item = entityManager.find(Item.class, itemId);

            if(item != null){
                return item.getTag();
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
    public Tag getTagByName(String tagName) {
        try{
            return entityManager.createQuery("FROM Tag WHERE name = :tagName", Tag.class)
                    .setParameter("tagName", tagName)
                    .getSingleResult();
        }
        catch (NoResultException e){
            throw new ResourceNotFoundException(e);
        }
        catch (NonUniqueResultException e){
            throw new DatabaseAccessException(e);
        }
        catch (Exception e){
            throw new GeneralCatchAllException(e);
        }
    }

    @Transactional
    @Override
    public Tag editTagById(Tag newTag, int tagId) {
        try{
            Tag tag = entityManager.find(Tag.class, tagId);

            if(tag != null){
                tag.setVersion(newTag.getVersion());
                tag.setName(newTag.getName());
                tag.setLastModifiedDate(LocalDateTime.now());

                return entityManager.merge(tag);
            }
            throw new ResourceNotFoundException("Resource: " + Tag.class + " || Not Found.");
        }
        catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException(e);
        }
        catch (Exception e){
            throw new GeneralCatchAllException(e);
        }
    }

    @Transactional
    @Override
    public int deleteTagById(int tagId) {
        try{

            //THIS REMOVES THE ASSOCIATION BETWEEN ITEM AND TAG
                //for loop is not efficient as it opens up a db connection for every update
            Tag currentTag = entityManager.find(Tag.class, tagId);
            if(currentTag != null){
                for (Item item : currentTag.getItems()){
                    item.setTag(null);
                    entityManager.merge(item);
                }
            }
            entityManager.flush();


//            //THIS REMOVES THE ASSOCIATION BETWEEN ITEM AND TAG WITH A BULK DB UPDATE
//            entityManager.createQuery("UPDATE Item i SET i.tag.id = null WHERE i.tag.id = :tagId")
//                    .setParameter("tagId", tagId)
//                    .executeUpdate();


            //THIS DELETES THE TAG
                //could also use entityManager.remove(currentTag), this has a void return value and this interface
                //requires an integer to be returned and executeUpdate will return the number of rows affected by the query
            int deletedCount = entityManager.createQuery("DELETE from Tag WHERE id= :tagId")
                    .setParameter("tagId", tagId)
                    .executeUpdate();

            if(deletedCount > 0){
                return deletedCount;
            }

            throw new ResourceNotFoundException("ARGS: " + tagId + " || " + " this person does not exist in the DB.");

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
    public Tag addTag(Tag newTag) {
        return null;
    }

    @Transactional
    @Override
    public ItemTagDTO connectItemToTag(int itemId, int tagId) {
        return null;
    }
}
