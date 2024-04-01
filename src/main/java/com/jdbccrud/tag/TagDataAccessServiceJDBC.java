package com.jdbccrud.tag;

import com.jdbccrud.item_tag.ItemTagDTO;
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
public class TagDataAccessServiceJDBC implements ITagDAO {

    private final JdbcTemplate jdbcTemplate;

    public TagDataAccessServiceJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Tag> getAllTags() {
        List<Tag> allTags = new ArrayList<>();

        String sql = """
                SELECT id, created_date, last_modified_date, name, version
                FROM tag;
                """;

        SqlRowSet rowSet =  jdbcTemplate.queryForRowSet(sql);

        while (rowSet.next()){
            allTags.add(mapRowToTag(rowSet));
        }

        return allTags;
    }

    @Override
    public Tag getTagById(int tagId) {

        String sql = """
                SELECT id, created_date, last_modified_date, name, version
                FROM tag
                WHERE id = ?;
                """;

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, tagId);

        if(rowSet.next()){
            return mapRowToTag(rowSet);
        }

        return null;
    }

    @Override
    public Tag getTagByItemId(int itemId) {

        ItemTagDTO itemTagDTO = null;

        String itemTagSql = """
                SELECT id, item_id, tag_id
                FROM item_tag
                WHERE item_id = ?
                """;

        SqlRowSet tagRowSet = jdbcTemplate.queryForRowSet(itemTagSql, itemId);

        if (tagRowSet.next()){
            itemTagDTO = ItemTagDTO.mapRowToItemTag(tagRowSet);
        }

        return getTagById(itemTagDTO.getTagId());

    }

    @Override
    public Tag getTagByName(String tagName) {

        String sql = """
                SELECT id, created_date, last_modified_date, name, version
                FROM tag
                WHERE name = ?;
                """;

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, tagName);

        if(rowSet.next()){
            return mapRowToTag(rowSet);
        }

        return null;    }

    @Override
    public Tag editTagById(Tag newTag, int tagId) {
        String updateSql = """
                UPDATE tag
                SET name = ?, version = ?, last_modified_date = ?
                WHERE id = ?;
                """;

        jdbcTemplate.update(updateSql, newTag.getName(), newTag.getVersion(), LocalDateTime.now(), tagId);

        return getTagById(tagId);
    }

    //TODO finish delete method
    @Override
    public int deleteTagById(int tagId) {
        //to delete a tag you must remove all references to that tag in other tables, namely the item_tag table
        String deleteSql = """
                DELETE FROM tag WHERE id = ?;
                """;

        return jdbcTemplate.update(deleteSql);
    }

    @Override
    public Tag addTag(Tag newTag) {
        Object[] params = new Object[]{LocalDateTime.now(), LocalDateTime.now(), newTag.getName(), newTag.getVersion()};

        String insertSql = """
                INSERT INTO tag (created_date, last_modified_date, name, version)
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

        return getTagById(keyHolder.getKey().intValue());
    }

    @Override
    public ItemTagDTO connectItemToTag(int itemId, int tagId){
        Object[] params = new Object[]{itemId, tagId};

        String insertSql = """
                INSERT INTO item_tag (item_id, tag_id)
                VALUES (?,?)
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

        String selectSql = """
                SELECT id, item_id, tag_id
                FROM item_tag
                WHERE id = ?;
                """;

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(selectSql, keyHolder.getKey().longValue());

        if(rowSet.next()){
            return ItemTagDTO.mapRowToItemTag(rowSet);
        }

        return null;
    }


    private Tag mapRowToTag(SqlRowSet rowSet) {
        Tag newTag = new Tag();

        newTag.id = rowSet.getInt("id");
        try {
            newTag.createdDate = rowSet.getTimestamp("created_date").toLocalDateTime();
            newTag.lastModifiedDate = rowSet.getTimestamp("last_modified_date").toLocalDateTime();
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
        newTag.name = rowSet.getString("name");
        newTag.version = rowSet.getInt("version");

        return newTag;
    }
}
