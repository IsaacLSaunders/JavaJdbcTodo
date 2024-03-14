package com.jdbccrud.tag;

import com.jdbccrud.item_tag.ItemTagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/tag")
public class TagController{

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public List<Tag> getAllTags() {
        return tagService.getAllTags();
    }

    @GetMapping("/tag_id/{tagId}")
    public Tag getTagById(@PathVariable int tagId) {
        return tagService.getTagById(tagId);
    }

    @GetMapping("/item_id/{itemId}")
    public Tag getTagByItemId(@PathVariable int itemId) {
        return tagService.getTagByItemId(itemId);
    }

    @GetMapping("/tag_name/{tagName}")
    public Tag getTagByName(@PathVariable String tagName) {
        return tagService.getTagByName(tagName);
    }

    @PutMapping("/{tagId}")
    public Tag editTagById(@RequestBody Tag newTag, @PathVariable int tagId) {
        return tagService.editTagById(newTag, tagId);
    }

    @DeleteMapping("/{tagId}")
    public int deleteTagById(@PathVariable int tagId) {
        return tagService.deleteTagById(tagId);
    }


    @PostMapping
    public Tag addTag(@RequestBody Tag newTag) {
        return tagService.addTag(newTag);
    }

    @PostMapping("/connect/{itemId}/{tagId}")
    public ItemTagDTO connectItemToTag(@PathVariable int itemId, @PathVariable int tagId){
        return tagService.connectItemToTag(itemId, tagId);
    }
}
