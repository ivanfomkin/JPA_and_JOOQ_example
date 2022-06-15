package com.github.ivanfomkin.jooqjpaexperiments.service;

import com.github.ivanfomkin.jooqjpaexperiments.entity.AuthorEntity;
import com.github.ivanfomkin.jooqjpaexperiments.repository.AuthorJpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

@Service
public class AuthorService {
    private static final String[] AUTHOR_FIRST_NAMES = {"Иван", "Пётр", "Сергей", "Николай", "Леонтий", "Павел", "Александр", "Владлен"};
    private static final String[] AUTHOR_LAST_NAMES = {"Иванов", "Петров", "Тугриков", "Шишкин", "Ландышев", "Сыроежкин", "Перцев", "Мышкин"};
    private static final int AUTHORS_COUNT = 30;

    private final AuthorJpaRepository authorJpaRepository;

    public AuthorService(AuthorJpaRepository authorJpaRepository) {
        this.authorJpaRepository = authorJpaRepository;
    }

    public List<AuthorEntity> generateAuthors() {
        List<AuthorEntity> authors = new ArrayList<>();
        RandomGenerator randomGenerator = RandomGenerator.getDefault();
        for (int i = 0; i < AUTHORS_COUNT; i++) {
            var author = new AuthorEntity();
            var authorName = AUTHOR_FIRST_NAMES[randomGenerator.nextInt(AUTHOR_FIRST_NAMES.length)] + " " + AUTHOR_LAST_NAMES[randomGenerator.nextInt(AUTHOR_LAST_NAMES.length)];
            author.setName(authorName);
            authors.add(author);
        }
        return authorJpaRepository.saveAll(authors);
    }
}
