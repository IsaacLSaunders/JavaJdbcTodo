package com.jdbccrud.person;

import java.util.List;

public interface IPersonDAO {

    List<Person> selectAllPersons();

    Person selectPersonById();

    Person selectPersonByUsername();

    int addPerson(Person person);

    boolean deletePerson();

    Person editPerson();

}
