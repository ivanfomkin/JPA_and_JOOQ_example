package com.github.ivanfomkin.jooqjpaexperiments.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorEntity author;

    @Override
    public String toString() {
        return "Книга № " + id + ", заголовок: " + title + ", автор: " + author.getName();
    }
}
