package com.paris.librarySystem.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;
    private String title;
    private int numberOfCopies;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}
