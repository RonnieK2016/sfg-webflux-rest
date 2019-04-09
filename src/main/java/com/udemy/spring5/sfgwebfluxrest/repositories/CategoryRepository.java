package com.udemy.spring5.sfgwebfluxrest.repositories;

import com.udemy.spring5.sfgwebfluxrest.domain.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CategoryRepository extends ReactiveMongoRepository<Category, String> {
}
