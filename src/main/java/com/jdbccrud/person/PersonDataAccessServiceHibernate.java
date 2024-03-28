package com.jdbccrud.person;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        TypedQuery<Person> query = entityManager.createQuery("FROM Person", Person.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Person addPerson(Person person) {
        person.setCreatedDate(LocalDateTime.now());
        person.setLastModifiedDate(person.getCreatedDate());
        entityManager.persist(person);

        return entityManager.find(Person.class, person.getId());
    }

    @Override
    public int deletePersonById(int personId) {
        return 0;
    }

    @Override
    public int deletePersonByUsername(String username) {
        return 0;
    }

    @Override
    public Person updatePerson(Person person, int personId) {
        return null;
    }

    @Override
    public Person getPersonByUsername(String username) {
        TypedQuery<Person> query = entityManager.createQuery(
                "FROM Person WHERE username='?1'", Person.class
        );

        return query.setParameter(1,username).getSingleResult();

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
