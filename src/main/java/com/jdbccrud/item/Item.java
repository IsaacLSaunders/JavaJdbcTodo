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

    public Item() {
    }

    public Item(int asigneeId, LocalDateTime createdDate, LocalDateTime lastModifiedDate, String description, String status, int version) {
        this.asigneeId = asigneeId;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.description = description;
        this.status = status;
        this.version = version;
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
                '}';
    }
}
