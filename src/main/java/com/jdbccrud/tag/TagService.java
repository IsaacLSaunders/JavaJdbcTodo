//package com.jdbccrud.tag;
//
//import com.jdbccrud.item_tag.ItemTagDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class TagService{
//
//    private final TagDataAccessServiceJDBC tagDataAccessServiceJDBC;
//
//    @Autowired
//    public TagService(TagDataAccessServiceJDBC tagDataAccessServiceJDBC) {
//        this.tagDataAccessServiceJDBC = tagDataAccessServiceJDBC;
//    }
//
//    public List<Tag> getAllTags() {
//        return tagDataAccessServiceJDBC.getAllTags();
//    }
//
//    public Tag getTagById(int tagId) {
//        return tagDataAccessServiceJDBC.getTagById(tagId);
//    }
//
//    public Tag getTagByItemId(int itemId) {
//        return tagDataAccessServiceJDBC.getTagByItemId(itemId);
//    }
//
//    public Tag getTagByName(String tagName) {
//        return tagDataAccessServiceJDBC.getTagByName(tagName);
//    }
//
//    public Tag editTagById(Tag newTag, int tagId) {
//        return tagDataAccessServiceJDBC.editTagById(newTag, tagId);
//    }
//
//    public int deleteTagById(int tagId) {
//        return tagDataAccessServiceJDBC.deleteTagById(tagId);
//    }
//
//    public Tag addTag(Tag newTag) {
//        return tagDataAccessServiceJDBC.addTag(newTag);
//    }
//
//    public ItemTagDTO connectItemToTag(int itemId, int tagId){
//        return tagDataAccessServiceJDBC.connectItemToTag(itemId, tagId);
//    }
//    }
