package com.jdbccrud.tag;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class Tag {

    public int id;

    public LocalDateTime createdDate;

    public LocalDateTime lastModifiedDate;

    public String name;

    public int version;

}
