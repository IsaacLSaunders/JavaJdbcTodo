package com.jdbccrud.person;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;

    @Column(name = "created_date")
    public LocalDateTime createdDate;

    @Column(name = "first_name")
    public String firstName;

    @Column(name = "last_name")
    public String lastName;

    @Column(name = "last_modified_date")
    public LocalDateTime lastModifiedDate;

    @Column(name = "username")
    public String username;

    @Column(name = "version")
    public int version;

    public Person() {
    }

    public Person(LocalDateTime createdDate, String firstName, String lastName, LocalDateTime lastModifiedDate, String username, int version) {
        this.createdDate = createdDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastModifiedDate = lastModifiedDate;
        this.username = username;
        this.version = version;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", createdDate=" + createdDate +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", lastModifiedDate=" + lastModifiedDate +
                ", username='" + username + '\'' +
                ", version=" + version +
                '}';
    }
}
