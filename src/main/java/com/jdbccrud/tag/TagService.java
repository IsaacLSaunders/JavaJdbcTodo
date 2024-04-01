package com.jdbccrud.tag;

import com.jdbccrud.item_tag.ItemTagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService{

    private final TagDataAccessServiceJDBC tagDataAccessServiceJDBC;

    private final TagDataAccessServiceHibernate tagDataAccessServiceHibernate;

    @Autowired
    public TagService(TagDataAccessServiceJDBC tagDataAccessServiceJDBC, TagDataAccessServiceHibernate tagDataAccessServiceHibernate) {
        this.tagDataAccessServiceJDBC = tagDataAccessServiceJDBC;
        this.tagDataAccessServiceHibernate = tagDataAccessServiceHibernate;
    }

    public List<Tag> getAllTags() {
        return tagDataAccessServiceHibernate.getAllTags();
    }

    public Tag getTagById(int tagId) {
        return tagDataAccessServiceHibernate.getTagById(tagId);
    }

    public Tag getTagByItemId(int itemId) {
        return tagDataAccessServiceHibernate.getTagByItemId(itemId);
    }

    public Tag getTagByName(String tagName) {
        return tagDataAccessServiceHibernate.getTagByName(tagName);
    }

    public Tag editTagById(Tag newTag, int tagId) {
        return tagDataAccessServiceHibernate.editTagById(newTag, tagId);
    }

    public int deleteTagById(int tagId) {
        return tagDataAccessServiceHibernate.deleteTagById(tagId);
    }

    public Tag addTag(Tag newTag) {
        return tagDataAccessServiceHibernate.addTag(newTag);
    }

    public ItemTagDTO connectItemToTag(int itemId, int tagId){
        return tagDataAccessServiceHibernate.connectItemToTag(itemId, tagId);
    }
    }
