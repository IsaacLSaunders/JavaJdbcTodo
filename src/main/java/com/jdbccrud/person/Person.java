package com.jdbccrud.person;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class Person {
    public int id;

    public LocalDateTime createdDate;

    public String firstName;

    public String lastName;

    public LocalDateTime lastModifiedDate;

    public String username;

    public int version;

}
