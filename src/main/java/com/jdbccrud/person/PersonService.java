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

    public void addNewPerson(Person person) {
            personDataAccessService.addPerson(person);
    }

}
