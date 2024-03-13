package com.jdbccrud.tag;

import com.jdbccrud.item_tag.ItemTagDTO;

import java.util.List;

public interface ITagDAO {

    public List<Tag> getAllTags();

    public Tag getTagById(int tagId);

    public Tag getTagByItemId(int itemId);

    public Tag getTagByName(String tagName);

    public Tag editTagById(Tag newTag, int tagId);

    public int deleteTagById(int tagId);

    public Tag addTag(Tag newTag);

    public ItemTagDTO connectItemToTag(int itemId, int tagId);


    }
