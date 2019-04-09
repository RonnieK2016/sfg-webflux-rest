package com.udemy.spring5.sfgwebfluxrest.controllers;

import com.udemy.spring5.sfgwebfluxrest.domain.Category;
import com.udemy.spring5.sfgwebfluxrest.repositories.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static reactor.core.publisher.Mono.when;

public class CategoryControllerTest {

    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryController categoryController;
    private WebTestClient webTestClient;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        webTestClient = WebTestClient.bindToController(categoryController).build();
    }

    @Test
    public void getAllCategories() {
        given(categoryRepository.findAll()).willReturn(
                Flux.just(
                        Category.builder().description("First").build(),
                        Category.builder().description("Second").build()));

        webTestClient.get().uri("/api/v1/categories/")
                .exchange()
                .expectBodyList(Category.class)
                .hasSize(2);
    }

    @Test
    public void getCategoryById() {

        given(categoryRepository.findById(anyString())).willReturn(
                Mono.just(Category.builder().description("First").build()));

        webTestClient.get().uri("/api/v1/categories/id")
                .exchange()
                .expectBodyList(Category.class);
    }
}