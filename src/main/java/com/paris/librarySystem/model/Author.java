package com.paris.librarySystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Long id;
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "author")
    private Set<Book> books;

}
