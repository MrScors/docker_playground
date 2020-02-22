package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "books_seq")
    @SequenceGenerator(name = "books_seq",
            sequenceName = "seq_book", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "author")
    private String author;
    @Column(name = "title")
    private String title;

    public Book(String author, String title) {
        this.author = author;
        this.title = title;
    }

    @Override
    public String toString() {
        return "{id = " + id + ", author = " + author + ", title = " + title + "}";
    }
}
