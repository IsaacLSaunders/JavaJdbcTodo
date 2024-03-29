package com.jdbccrud.person;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonDataAccessServiceJDBC implements IPersonDAO {

    private final JdbcTemplate jdbcTemplate;

    public PersonDataAccessServiceJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Person> selectAllPersons() {
        List<Person> allPersons = new ArrayList<>();

        String newPersonSql = """
                SELECT id, created_date, last_modified_date, first_name, last_name, username, version
                FROM person;
                """;
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(newPersonSql);


        while (rowSet.next()) {
            allPersons.add(mapRowToPerson(rowSet));
        }

        return allPersons;
    }


    @Override
    public Person addPerson(Person person) {

        Object[] params = new Object[]{person.getVersion(), person.getUsername(), person.getFirstName(), person.getLastName()};

        String insertSql = """
                INSERT INTO person(version, username, first_name, last_name)
                VALUES (?,?,?,?) RETURNING id;
                """;

        //jdbcTemplate.update has an overloaded method that takes a callback and a KeyHolder as parameters
        //this method gives us the ability to use RETURNING from our postgreSQL statement
        //getting us the autogenerated id from the database without having to do another query with the username
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(insertSql, PreparedStatement.RETURN_GENERATED_KEYS);
            //we need to set the parameters for the prepared statement manually when using this overloaded method
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            return ps;
        }, keyHolder);

        String newPersonSql = """
                SELECT id, created_date, last_modified_date, first_name, last_name, username, version
                FROM person WHERE id = ?;
                """;
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(newPersonSql, keyHolder.getKey().longValue());


        if (rowSet.next()) {
            return mapRowToPerson(rowSet);
        }

        return null;
    }

    //TODO finish delete person by id method
    @Override
    public int deletePersonById(int personId) {
        //to delete a person you must remove all references to the person in foreign keys in other tables
        String sql = """
                DELETE FROM person WHERE id = ?;
                """;
        return jdbcTemplate.update(sql, personId);
    }

    //TODO finish delete person by username method
    @Override
    public int deletePersonByUsername(String username) {
        //to delete a person you must remove all references to the person in foreign keys in other tables
        String sql = """
                DELETE FROM person WHERE username = ?;
                """;
        return jdbcTemplate.update(sql, username);
    }

    @Override
    public Person updatePerson(Person person, int personId) {
        String updateSql = """
                UPDATE person
                SET first_name = ?, last_name = ?, username = ?, last_modified_date = ?
                WHERE id = ?;
                """;
        String retrieveNewPersonSql = """
                SELECT id, created_date, last_modified_date, first_name, last_name, username, version
                FROM person
                WHERE id = ?;
                """;

        jdbcTemplate.update(updateSql, person.getFirstName(), person.getLastName(), person.getUsername(), LocalDateTime.now(), personId);

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(retrieveNewPersonSql, personId);

        if (rowSet.next()) {
            return mapRowToPerson(rowSet);
        }

        return null;

    }

    @Override
    public Person getPersonByUsername(String username) {
        String sql = """
                SELECT id, created_date, last_modified_date, first_name, last_name, username, version
                FROM person
                WHERE username = ?;
                """;

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, username);

        if (rowSet.next()) {
            return mapRowToPerson(rowSet);
        }

        return null;
    }

    @Override
    public Person getPersonById(int id) {
        String sql = """
                SELECT id, created_date, last_modified_date, first_name, last_name, username, version
                FROM person
                WHERE id = ?;
                """;

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, id);

        if (rowSet.next()) {
            return mapRowToPerson(rowSet);
        }

        return null;
    }

    private Person mapRowToPerson(SqlRowSet rowSet) {
        Person newPerson = new Person();

        newPerson.setId(rowSet.getInt("id"));
        try {
            newPerson.setCreatedDate(rowSet.getTimestamp("created_date").toLocalDateTime());
            newPerson.setLastModifiedDate(rowSet.getTimestamp("last_modified_date").toLocalDateTime());
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        newPerson.setUsername(rowSet.getString("username"));
        newPerson.setFirstName(rowSet.getString("first_name"));
        newPerson.setLastName(rowSet.getString("last_name"));
        newPerson.setVersion(rowSet.getInt("version"));

        return newPerson;
    }
}
