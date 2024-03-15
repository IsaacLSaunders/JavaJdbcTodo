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
    public int id;

    @Column(name = "created_date")
    public LocalDateTime createdDate;

    @Column(name = "last_modified_date")
    public LocalDateTime lastModifiedDate;

    public String name;

    public int version;

}
