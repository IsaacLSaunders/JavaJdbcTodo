package com.jdbccrud.person;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
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
    public Person addPerson(Person person) {
        String sql = """
                INSERT INTO person(version, username, first_name, last_name)
                VALUES (?,?,?,?) RETURNING id;
                """;
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, person.version, person.username, person.firstName, person.lastName);

        if(rowSet.next()){
            return mapRowToPerson(rowSet);
        }

        return null;
    }

    @Override
    public int deletePersonById(int personId) {
        String sql = """
                DELETE FROM person WHERE id = ?;
                """;
        return jdbcTemplate.update(sql, personId);
    }

    @Override
    public int deletePersonByUsername(String username) {
        String sql = """
                DELETE FROM person WHERE username = ?;
                """;
        return jdbcTemplate.update(sql, username);
    }

    @Override
    public Person editPerson() {
        return null;
    }

    private Person mapRowToPerson(SqlRowSet rowSet){
        Person newPerson = new Person();

        newPerson.setId(rowSet.getInt("id"));
        try {
            newPerson.setCreatedDate(rowSet.getTimestamp("created_date").toLocalDateTime());
            newPerson.setLastModifiedDate(rowSet.getTimestamp("last_modified_date").toLocalDateTime());
        } catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
        newPerson.setUsername(rowSet.getString("username"));
        newPerson.setFirstName(rowSet.getString("first_name"));
        newPerson.setLastName(rowSet.getString("last_name"));
        newPerson.setVersion(rowSet.getInt("version"));

        return newPerson;
    }
}
