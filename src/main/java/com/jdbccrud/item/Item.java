package com.jdbccrud.item;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Setter
@Getter
public class Item {

    public int id;

    public int asigneeId;

    public LocalDateTime createdDate;

    public LocalDateTime lastModifiedDate;

    public String description;

    public String status;

    public int version;



}
