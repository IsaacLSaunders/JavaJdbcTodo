package com.jdbccrud.person;

import java.util.List;

public interface IPersonDAO {

    List<Person> selectAllPersons();

    Person selectPersonById();

    Person selectPersonByUsername();

    Person addPerson(Person person);

    int deletePersonById(int personId);

    int deletePersonByUsername(String username);

    Person editPerson();

}
