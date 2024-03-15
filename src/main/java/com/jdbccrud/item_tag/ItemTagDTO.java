package com.jdbccrud.item_tag;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.jdbc.support.rowset.SqlRowSet;

@Getter
@Setter
@Entity
@Table(name = "item_tag")
public class ItemTagDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "item_id")
    public int itemId;

    @Column(name = "tag_id")
    public int tagId;

    public static ItemTagDTO mapRowToItemTag(SqlRowSet rowSet){
        ItemTagDTO newItemTagDTO = new ItemTagDTO();

        newItemTagDTO.id = rowSet.getInt("id");
        newItemTagDTO.itemId = rowSet.getInt("item_id");
        newItemTagDTO.tagId = rowSet.getInt("tag_id");

        return newItemTagDTO;
    }

}
