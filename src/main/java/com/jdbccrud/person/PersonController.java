package com.jdbccrud.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @PostMapping
    public Person addPerson(@RequestBody Person person){
        return personService.addNewPerson(person);
    }

    @DeleteMapping("/{personId}")
    public void deletePersonById(@PathVariable int personId){
        personService.deletePersonById(personId);
    }

    @DeleteMapping("/{username}")
    public void deletePersonByUsername(@PathVariable String username){
        personService.deletePersonByUsername(username);
    }
}
