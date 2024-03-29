package com.jdbccrud.item;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.jdbccrud.tag.Tag;
import com.jdbccrud.utility.MigrationFileUtil;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "item")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;

    @Column(name = "asignee_id")
    public int asigneeId;

    @Column(name = "created_date")
    public LocalDateTime createdDate;

    @Column(name = "last_modified_date")
    public LocalDateTime lastModifiedDate;

    @Column(name = "description")
    public String description;

    @Column(name = "status")
    public String status;

    @Column(name = "version")
    public int version;

    @ManyToOne()
    @JoinColumn(name = "tag_id")
    public Tag tag;

    public Item() {
        this.version =  MigrationFileUtil.countMigrationFiles();
    }

    public Item(int asigneeId, LocalDateTime createdDate, LocalDateTime lastModifiedDate, String description, String status, int version, Tag tag) {
        this.asigneeId = asigneeId;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.description = description;
        this.status = status;
        this.version = version;
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", asigneeId=" + asigneeId +
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", version=" + version +
                ", tag=" + tag +
                '}';
    }
}
