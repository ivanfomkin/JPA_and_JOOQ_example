package com.github.ivanfomkin.jooqjpaexperiments.service;


import com.github.ivanfomkin.jooqjpaexperiments.entity.BookEntity;
import com.github.ivanfomkin.jooqjpaexperiments.repository.BookJooqRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DataBaseExecutor implements CommandLineRunner {
    private final BookService bookService;
    private final BookJooqRepository bookJooqRepository;

    public DataBaseExecutor(BookService bookService, BookJooqRepository bookJooqRepository) {
        this.bookService = bookService;
        this.bookJooqRepository = bookJooqRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        bookService.generateBooks();
        for (BookEntity book : bookJooqRepository.executeAllBooks()) {
            log.info(book.toString());
        }
    }

}
