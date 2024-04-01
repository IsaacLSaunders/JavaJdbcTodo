package com.jdbccrud.person;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.jdbccrud.item.Item;
import com.jdbccrud.item_tag.ItemTagDTO;
import com.jdbccrud.tag.Tag;
import com.jdbccrud.utility.MigrationFileUtil;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "person")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@NamedQuery(name = "Person.findByUsername", query = "SELECT p FROM Person p WHERE p.username = :username")
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;

    @Column(name = "created_date")
    public LocalDateTime createdDate;

    @NotNull
    @Column(name = "first_name", nullable = false)
    public String firstName;

    @NotNull
    @Column(name = "last_name", nullable = false)
    public String lastName;

    @Column(name = "last_modified_date")
    public LocalDateTime lastModifiedDate;

    @NotNull
    @Column(name = "username", nullable = false)
    public String username;

    @Column(name = "version", nullable = false)
    public int version;

    @OneToMany(mappedBy = "asigneeId", cascade = CascadeType.REMOVE)
    private List<Item> items = new ArrayList<>();


    public Person() {
        this.version =  MigrationFileUtil.countMigrationFiles();
    }

    public Person(LocalDateTime createdDate, String firstName, String lastName, LocalDateTime lastModifiedDate, String username, int version, List<Item> items) {
        this.createdDate = createdDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastModifiedDate = lastModifiedDate;
        this.username = username;
        this.version = version;
        this.items = items;
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
                ", items=" + items +
                '}';
    }
}
