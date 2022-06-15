package com.github.ivanfomkin.jooqjpaexperiments.repository;

import com.github.ivanfomkin.jooqjpaexperiments.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorJpaRepository extends JpaRepository<AuthorEntity, Integer> {
}
