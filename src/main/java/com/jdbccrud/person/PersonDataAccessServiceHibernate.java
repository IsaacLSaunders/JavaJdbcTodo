package com.jdbccrud.person;

import com.jdbccrud.exception.DatabaseAccessException;
import com.jdbccrud.exception.ResourceNotFoundException;
import com.jdbccrud.tag.Tag;
import com.jdbccrud.utility.LoggerUtil;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PersonDataAccessServiceHibernate implements IPersonDAO{

    private final EntityManager entityManager;

    @Autowired
    public PersonDataAccessServiceHibernate(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Person> selectAllPersons() {
        try{
            TypedQuery<Person> query = entityManager.createQuery("FROM Person ORDER BY username", Person.class);
            return query.getResultList();
        } catch (Exception e){
            throw new DatabaseAccessException("Error accessing the database || " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public Person addPerson(Person person) {
        try {
            person.setCreatedDate(LocalDateTime.now());
            person.setLastModifiedDate(person.getCreatedDate());
            entityManager.persist(person);

            return entityManager.find(Person.class, person.getId());
        } catch (Exception e){
            throw new DatabaseAccessException("Error accessing the database || ARGS: " + person + " || " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public int deletePersonById(int personId) {
        try {
            Person person = entityManager.find(Person.class, personId);
            if(person != null){
                entityManager.remove(person);
                return 1;
            } else {
                //I want to throw an exception for logging purposes if the user inputs a bad userid into the delete person endpoint
                throw new Exception("Error accessing the database || ARGS: " + personId + " || " + " this person does not exist in the DB.");
            }
            //this exception is in case anything goes wrong while executing any of the JPA functionality like a PersistenceException or IllegalStateException
        } catch (Exception e){
            throw new DatabaseAccessException(e.getMessage());
        }

    }

    @Override
    @Transactional
    public int deletePersonByUsername(String username) {
        try {
            int deletedCount = entityManager.createQuery(
                            "DELETE FROM Person WHERE username = :username"
                    )
                    .setParameter("username", username)
                    .executeUpdate();

            if(deletedCount != 0){
                return deletedCount;
            }
            //I want to throw an exception for logging purposes if the user inputs a bad userid into the delete person endpoint
            throw new ResourceNotFoundException("ARGS: " + username + " || " + " this person does not exist in the DB.");
        //this exception is in case anything goes wrong while executing any of the JPA functionality like a PersistenceException or IllegalStateException
        }
        catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException(e);
        }
        catch (Exception e){
            throw new DatabaseAccessException(e);
        }
    }

    @Override
    @Transactional
    public Person updatePerson(Person person, int personId) {
        try {
            Person currentPerson = entityManager.find(Person.class, personId);

            if(currentPerson != null){
                currentPerson.setFirstName(person.getFirstName());
                currentPerson.setLastName(person.getLastName());
                currentPerson.setVersion(person.getVersion());
                currentPerson.setLastModifiedDate(LocalDateTime.now());

                return entityManager.merge(currentPerson);
            }
            throw new ResourceNotFoundException("Resource: " + Person.class + " || Not Found.");
        }
        catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException(e);
        }
        catch (Exception e){
            throw new DatabaseAccessException("Error accessing the database || ARGS: " + personId + " || " + e.getMessage(), e);
        }
    }

    @Override
    public Person getPersonByUsername(String username) {
        try {
            TypedQuery<Person> query = entityManager.createQuery(
                    "FROM Person WHERE username=?1", Person.class
            );

            return query.setParameter(1,username).getSingleResult();
        } catch (Exception e){
            throw new DatabaseAccessException("Error accessing the database || ARGS: " + username + " || " + e.getMessage(), e);
        }


        //parameters can also be added using :<String>
//        TypedQuery<Person> query = entityManager.createQuery(
//                "FROM Person WHERE username=:username", Person.class
//        );
//
//        return query.setParameter("username", username).getSingleResult();
    }

    @Override
    public Person getPersonById(int id) {
        return entityManager.find(Person.class, id);
    }
}
