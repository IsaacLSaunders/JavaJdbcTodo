package com.jdbccrud.tag;

import com.jdbccrud.item_tag.ItemTagDTO;

import java.util.List;

public class TagDataAccessServiceHibernate implements ITagDAO{
    @Override
    public List<Tag> getAllTags() {
        return null;
    }

    @Override
    public Tag getTagById(int tagId) {
        return null;
    }

    @Override
    public Tag getTagByItemId(int itemId) {
        return null;
    }

    @Override
    public Tag getTagByName(String tagName) {
        return null;
    }

    @Override
    public Tag editTagById(Tag newTag, int tagId) {
        return null;
    }

    @Override
    public int deleteTagById(int tagId) {
        return 0;
    }

    @Override
    public Tag addTag(Tag newTag) {
        return null;
    }

    @Override
    public ItemTagDTO connectItemToTag(int itemId, int tagId) {
        return null;
    }
}
