package com.jdbccrud.tag;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "tag")
public class Tag {

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

    public Tag() {
    }

    public Tag(LocalDateTime createdDate, LocalDateTime lastModifiedDate, String name, int version) {
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.name = name;
        this.version = version;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                ", name='" + name + '\'' +
                ", version=" + version +
                '}';
    }
}
