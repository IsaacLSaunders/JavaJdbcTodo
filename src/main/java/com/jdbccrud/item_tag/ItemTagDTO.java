package com.jdbccrud.item_tag;

import lombok.Getter;
import lombok.Setter;
import org.springframework.jdbc.support.rowset.SqlRowSet;

@Getter
@Setter
public class ItemTagDTO {

    public int id;

    public int itemId;

    public int tagId;

    public static ItemTagDTO mapRowToItemTag(SqlRowSet rowSet){
        ItemTagDTO newItemTagDTO = new ItemTagDTO();

        newItemTagDTO.id = rowSet.getInt("id");
        newItemTagDTO.itemId = rowSet.getInt("item_id");
        newItemTagDTO.tagId = rowSet.getInt("tag_id");

        return newItemTagDTO;
    }

}
