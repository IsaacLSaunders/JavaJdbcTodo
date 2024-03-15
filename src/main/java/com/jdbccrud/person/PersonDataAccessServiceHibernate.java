package com.jdbccrud.person;

import java.util.List;

public class PersonDataAccessServiceHibernate implements IPersonDAO{
    @Override
    public List<Person> selectAllPersons() {
        return null;
    }

    @Override
    public Person addPerson(Person person) {
        return null;
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
        return null;
    }

    @Override
    public Person getPersonById(int id) {
        return null;
    }
}
