package com.jdbccrud.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Setter
@Getter
@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "asignee_id")
    public int asigneeId;

    @Column(name = "created_date")
    public LocalDateTime createdDate;

    @Column(name = "last_modified_date")
    public LocalDateTime lastModifiedDate;

    public String description;

    public String status;

    public int version;

}
