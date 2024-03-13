package com.jdbccrud.tag;

import com.jdbccrud.item_tag.ItemTagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class TagService{

    private final TagDataAccessService tagDataAccessService;

    @Autowired
    public TagService(TagDataAccessService tagDataAccessService) {
        this.tagDataAccessService = tagDataAccessService;
    }

    public List<Tag> getAllTags() {
        return tagDataAccessService.getAllTags();
    }

    public Tag getTagById(int tagId) {
        return tagDataAccessService.getTagById(tagId);
    }

    public Tag getTagByItemId(int itemId) {
        return tagDataAccessService.getTagByItemId(itemId);
    }

    public Tag getTagByName(String tagName) {
        return tagDataAccessService.getTagByName(tagName);
    }

    public Tag editTagById(Tag newTag, int tagId) {
        return tagDataAccessService.editTagById(newTag, tagId);
    }

    public int deleteTagById(int tagId) {
        return tagDataAccessService.deleteTagById(tagId);
    }

    public Tag addTag(Tag newTag) {
        return tagDataAccessService.addTag(newTag);
    }

    public ItemTagDTO connectItemToTag(int itemId, int tagId){
        return tagDataAccessService.connectItemToTag(itemId, tagId);
    }
    }
