package com.jdbccrud.person;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonDataAccessService implements IPersonDAO{

    private final JdbcTemplate jdbcTemplate;

    public PersonDataAccessService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Person> selectAllPersons() {
        return null;
    }

    @Override
    public Person selectPersonById() {
        return null;
    }

    @Override
    public Person selectPersonByUsername() {
        return null;
    }

    @Override
    public int addPerson(Person person) {
        String sql = """
                INSERT INTO person(version, username, first_name, last_name)
                VALUES (?,?,?,?)
                """;
        return jdbcTemplate.update(sql, person.version, person.username, person.firstName, person.lastName);
    }

    @Override
    public boolean deletePerson() {
        return false;
    }

    @Override
    public Person editPerson() {
        return null;
    }
}
