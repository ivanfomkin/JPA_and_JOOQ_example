package com.github.ivanfomkin.jooqjpaexperiments.repository;

import com.github.ivanfomkin.jooqjpaexperiments.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookJpaRepository extends JpaRepository<BookEntity, Integer> {
}
