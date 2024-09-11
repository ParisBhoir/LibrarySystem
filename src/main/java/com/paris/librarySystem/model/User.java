package com.paris.librarySystem.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "user")
    private Set<BorrowRecord> borrowRecords;
}
