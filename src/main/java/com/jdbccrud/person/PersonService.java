package com.jdbccrud.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
