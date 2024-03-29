package com.jdbccrud.tag;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.jdbccrud.item.Item;
import com.jdbccrud.item_tag.ItemTagDTO;
import com.jdbccrud.utility.MigrationFileUtil;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "tag")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Tag implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;

    @Column(name = "created_date")
    public LocalDateTime createdDate;

    @Column(name = "last_modified_date")
    public LocalDateTime lastModifiedDate;

    @Column(name = "name")
    public String name;

    @Column(name = "version")
    public int version;

    @JsonIgnore
    @OneToMany(mappedBy = "tag")
    public List<Item> items = new ArrayList<>();


    public Tag() {
        this.version =  MigrationFileUtil.countMigrationFiles();
    }

    public Tag(LocalDateTime createdDate, LocalDateTime lastModifiedDate, String name, int version, List<Item> items) {
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.name = name;
        this.version = version;
        this.items = items;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                ", name='" + name + '\'' +
                ", version=" + version +
                ", items=" + items +
                '}';
    }
}
