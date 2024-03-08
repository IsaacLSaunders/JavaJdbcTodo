package com.jdbccrud.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
private final PersonDataAccessService personDataAccessService;

    @Autowired
    public PersonService(PersonDataAccessService personDataAccessService) {
        this.personDataAccessService = personDataAccessService;
    }

    public Person addNewPerson(Person person) {
            return personDataAccessService.addPerson(person);
    }

    public void deletePersonById(int personId){
        personDataAccessService.deletePersonById(personId);
    }

    public void deletePersonByUsername(String username){
        personDataAccessService.deletePersonByUsername(username);
    }

    public List<Person> getAllPersons(){
        return personDataAccessService.selectAllPersons();
    }

    public Person updatePerson(Person person, int personId) {
        return personDataAccessService.updatePerson(person, personId);
    }

    public Person getPersonByUsername(String username) {
        return personDataAccessService.getPersonByUsername(username);
    }

    public Person getPersonById(int id) {
        return personDataAccessService.getPersonById(id);
    }
}
