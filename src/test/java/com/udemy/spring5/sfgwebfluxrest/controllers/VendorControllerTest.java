package com.udemy.spring5.sfgwebfluxrest.controllers;

import com.udemy.spring5.sfgwebfluxrest.domain.Vendor;
import com.udemy.spring5.sfgwebfluxrest.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

public class VendorControllerTest {

    @Mock
    private VendorRepository vendorRepository;
    @InjectMocks
    private VendorController vendorController;
    private WebTestClient webTestClient;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        webTestClient = WebTestClient.bindToController(vendorController).build();
    }

    @Test
    public void getAllVendors() {

        given(vendorRepository.findAll()).willReturn(
                Flux.just(
                        Vendor.builder().firstName("First Name").lastName("Last Name").build(),
                        Vendor.builder().firstName("First Name 1").lastName("Last Name 1").build()
                )
        );

        webTestClient.get().uri("/api/v1/vendors")
                .exchange()
                .expectBodyList(Vendor.class)
                .hasSize(2);

    }

    @Test
    public void getVendorById() {
        given(vendorRepository.findById(anyString())).willReturn(
                Mono.just(Vendor.builder().firstName("First Name").lastName("Last Name").build())
        );

        webTestClient.get().uri("/api/v1/vendors/id")
                .exchange()
                .expectBody(Vendor.class);
    }
}