package com.jdbccrud.person;

import java.util.List;

public interface IPersonDAO {

    List<Person> selectAllPersons();

    Person addPerson(Person person);

    int deletePersonById(int personId);

    int deletePersonByUsername(String username);

    Person updatePerson(Person person, int personId);

    Person getPersonByUsername(String username);

    Person getPersonById(int id);
}
