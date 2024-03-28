package com.jdbccrud.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PersonService {
private final PersonDataAccessServiceJDBC personDataAccessServiceJDBC;
private final PersonDataAccessServiceHibernate personDataAccessServiceHibernate;

    @Autowired
    public PersonService(PersonDataAccessServiceJDBC personDataAccessServiceJDBC, PersonDataAccessServiceHibernate personDataAccessServiceHibernate) {
        this.personDataAccessServiceJDBC = personDataAccessServiceJDBC;
        this.personDataAccessServiceHibernate = personDataAccessServiceHibernate;
    }

    public Person addNewPerson(Person person) {
            return personDataAccessServiceHibernate.addPerson(person);
    }

    public void deletePersonById(int personId){
        personDataAccessServiceJDBC.deletePersonById(personId);
    }

    public void deletePersonByUsername(String username){
        personDataAccessServiceJDBC.deletePersonByUsername(username);
    }

    public List<Person> getAllPersons(){
        return personDataAccessServiceHibernate.selectAllPersons();
    }

    public Person updatePerson(Person person, int personId) {
        return personDataAccessServiceJDBC.updatePerson(person, personId);
    }

    public Person getPersonByUsername(String username) {
        return personDataAccessServiceJDBC.getPersonByUsername(username);
    }

    public Person getPersonById(int id) {
        return personDataAccessServiceHibernate.getPersonById(id);
    }
}
