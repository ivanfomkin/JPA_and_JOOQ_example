package com.github.ivanfomkin.jooqjpaexperiments.service;

import com.github.ivanfomkin.jooqjpaexperiments.entity.AuthorEntity;
import com.github.ivanfomkin.jooqjpaexperiments.entity.BookEntity;
import com.github.ivanfomkin.jooqjpaexperiments.repository.BookJpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

@Service
public class BookService {
    private static final int BOOK_COUNT = 100;
    private final AuthorService authorService;
    private final BookJpaRepository bookJpaRepository;

    public BookService(AuthorService authorService, BookJpaRepository bookJpaRepository) {
        this.authorService = authorService;
        this.bookJpaRepository = bookJpaRepository;
    }

    public List<BookEntity> generateBooks() {
        RandomGenerator randomGenerator = RandomGenerator.getDefault();
        List<AuthorEntity> authorEntities = authorService.generateAuthors();
        List<BookEntity> bookList = new ArrayList<>();
        for (int i = 0; i < BOOK_COUNT; ) {
            BookEntity book = new BookEntity();
            book.setTitle("Book № " + ++i);
            bookList.add(book);
            book.setAuthor(authorEntities.get(randomGenerator.nextInt(authorEntities.size())));
        }
        return bookJpaRepository.saveAll(bookList);
    }
}
