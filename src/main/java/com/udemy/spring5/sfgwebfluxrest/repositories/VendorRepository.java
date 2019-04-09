package com.udemy.spring5.sfgwebfluxrest.repositories;

import com.udemy.spring5.sfgwebfluxrest.domain.Vendor;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface VendorRepository extends ReactiveMongoRepository<Vendor, String> {
}
