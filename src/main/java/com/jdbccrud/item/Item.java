package com.jdbccrud.item;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


public class Item {

    @Getter
    @Setter
    public int id;

    @Getter
    @Setter
    public int asigneeId;

    @Getter
    @Setter
    public LocalDateTime createdDate;

    @Getter
    @Setter
    public LocalDateTime lastModifiedDate;

    @Getter
    @Setter
    public String description;

    @Getter
    @Setter
    public String status;

    @Getter
    @Setter
    public int version;



}
