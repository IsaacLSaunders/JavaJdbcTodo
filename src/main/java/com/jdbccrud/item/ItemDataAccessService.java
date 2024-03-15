package com.jdbccrud.item;

import com.jdbccrud.person.Person;
import com.jdbccrud.person.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemDataAccessService implements IItemDAO {
    private final JdbcTemplate jdbcTemplate;
    private final PersonService personService;

    public ItemDataAccessService(JdbcTemplate jdbcTemplate, PersonService personService) {
        this.jdbcTemplate = jdbcTemplate;
        this.personService = personService;
    }

    @Override
    public List<Item> getAllItems() {
        List<Item> returnList = new ArrayList<>();

        String sql = """
                SELECT id, asignee_id, created_date, last_modified_date, description, status, version
                FROM item
                ORDER BY id;
                """;

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);

        while (rowSet.next()) {
            returnList.add(mapRowToItem(rowSet));
        }

        return returnList;
    }

    @Override
    public List<Item> getAllItemsByUsername(String username) {
        List<Item> returnList = new ArrayList<>();

        String sql = """
                SELECT i.id, i.asignee_id, i.created_date, i.last_modified_date, i.description, i.status, i.version
                FROM item as i
                JOIN person ON i.asignee_id = person.id
                WHERE person.username = ?
                ORDER BY i.id;
                """;

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, username);

        while (rowSet.next()) {
            returnList.add(mapRowToItem(rowSet));
        }

        return returnList;
    }

    @Override
    public List<Item> getAllItemsByUserId(int userId) {
        List<Item> returnList = new ArrayList<>();

        String sql = """
                SELECT i.id, i.asignee_id, i.created_date, i.last_modified_date, i.description, i.status, i.version
                FROM item as i
                JOIN person ON i.asignee_id = person.id
                WHERE person.id = ?
                ORDER BY i.id;
                """;

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);

        while (rowSet.next()) {
            returnList.add(mapRowToItem(rowSet));
        }

        return returnList;
    }

    @Override
    public Item addItemByUsername(Item newItem, String username) {

            //use ResponseEntity and specific Exception type to handle bad username and return back the correct Http status code
//        try {
            Person person = personService.getPersonByUsername(username);

            Object[] params = new Object[]{person.getId(), newItem.getDescription(), newItem.getStatus(), newItem.getVersion()};

            String insertSql = """
                INSERT INTO item (asignee_id, description, status, version)
                VALUES (?,?,?,?)
                RETURNING id;
                """;

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
                SELECT id, created_date, last_modified_date, asignee_id, description, status, version
                FROM item WHERE id = ?;
                """;
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(newPersonSql, keyHolder.getKey().longValue());


            if (rowSet.next()) {
                return mapRowToItem(rowSet);
            }

//        }
//        catch (Exception e){
//            if(e.){
//                return ResponseEntity<>(item, HttpStatus.BAD_REQUEST);
//            }
//        }


        return null;
    }

    @Override
    public Item addItemByUserId(Item newItem, int id) {

        Object[] params = new Object[]{id, newItem.getDescription(), newItem.getStatus(), newItem.getVersion()};

        String insertSql = """
                INSERT INTO item (asignee_id, description, status, version)
                VALUES (?,?,?,?)
                RETURNING id;
                """;

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
                SELECT id, created_date, last_modified_date, asignee_id, description, status, version
                FROM item WHERE id = ?;
                """;
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(newPersonSql, keyHolder.getKey().longValue());


        if (rowSet.next()) {
            return mapRowToItem(rowSet);
        }

        return null;
    }

    @Override
    public Item editItemByItemId(Item editedItem, int itemId) {
        String updateSql = """
                UPDATE item
                SET description = ?, status = ?, last_modified_date = ?
                WHERE id = ?;
                """;
        String retrieveNewItemSql = """
                SELECT id, asignee_id, created_date, last_modified_date, description, status, version
                FROM item
                WHERE id = ?;
                """;

        jdbcTemplate.update(updateSql, editedItem.getDescription(), editedItem.getStatus(), LocalDateTime.now(), itemId);

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(retrieveNewItemSql, itemId);

        if (rowSet.next()) {
            return mapRowToItem(rowSet);
        }

        return null;
    }

    //TODO finish delete item by item id method
    @Override
    public int deleteItemByItemId(int itemId) {

        //this method needs to also delete all instances of attached tags for the item because of the
            //foreign key constraint
        String sql = """
                DELETE FROM item WHERE id = ?;
                """;
        return jdbcTemplate.update(sql, itemId);
    }

    //TODO finish delete all items by person id method
    @Override
    public int deleteAllItemsByPersonId(int personId) {
        String sql = """
                DELETE FROM item WHERE asingee_id = ?;
                """;
        return jdbcTemplate.update(sql, personId);
    }

    private Item mapRowToItem(SqlRowSet rowSet) {
        Item newItem = new Item();

        newItem.setId(rowSet.getInt("id"));
        newItem.setAsigneeId(rowSet.getInt("asignee_id"));
        try {
            newItem.setCreatedDate(rowSet.getTimestamp("created_date").toLocalDateTime());
            newItem.setLastModifiedDate(rowSet.getTimestamp("last_modified_date").toLocalDateTime());
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        newItem.setDescription(rowSet.getString("description"));
        newItem.setStatus(rowSet.getString("status"));
        newItem.setVersion(rowSet.getInt("version"));

        return newItem;
    }
}
