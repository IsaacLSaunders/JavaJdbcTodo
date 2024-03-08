package com.jdbccrud.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public Person addPerson(@RequestBody Person person) {
        return personService.addNewPerson(person);
    }

    @PutMapping("/id/{personId}")
    public Person updatePerson(@RequestBody Person person, @PathVariable int personId) {
        return personService.updatePerson(person, personId);
    }

    @DeleteMapping("/id/{personId}")
    public void deletePersonById(@PathVariable int personId) {
        personService.deletePersonById(personId);
    }

    @DeleteMapping("/username/{username}")
    public void deletePersonByUsername(@PathVariable String username) {
        personService.deletePersonByUsername(username);
    }

    @GetMapping("/all")
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/username/{username}")
    public Person getPersonByUsername(@PathVariable String username) {
        return personService.getPersonByUsername(username);
    }

    @GetMapping("/id/{id}")
    public Person getPersonByUsername(@PathVariable int id) {
        return personService.getPersonById(id);
    }

}
