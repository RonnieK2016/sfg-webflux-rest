package com.udemy.spring5.sfgwebfluxrest.controllers;

import com.udemy.spring5.sfgwebfluxrest.domain.Vendor;
import com.udemy.spring5.sfgwebfluxrest.repositories.VendorRepository;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/vendors")
public class VendorController {

    private final VendorRepository vendorRepository;

    public VendorController(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    @GetMapping("{id}")
    public Mono<Vendor> getVendorById(@PathVariable String id) {
        return vendorRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> saveVendor(@RequestBody Publisher<Vendor> vendorPublisher) {
        return vendorRepository.saveAll(vendorPublisher).then();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Vendor> updateVendor(@PathVariable String id, @RequestBody Vendor vendor) {
        vendor.setId(id);
        return vendorRepository.save(vendor);
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Vendor> patchVendor(@PathVariable String id, @RequestBody Vendor vendor) {
        Vendor savedVendor = vendorRepository.findById(id).block();

        boolean isUpdated = false;

        if(savedVendor != null)
        {
            if(!StringUtils.isEmpty(vendor.getLastName()) && !vendor.getLastName().equals(savedVendor.getLastName())) {
                savedVendor.setLastName(vendor.getLastName());
                isUpdated = true;
            }

            if(!StringUtils.isEmpty(vendor.getFirstName()) && !vendor.getFirstName().equals(savedVendor.getFirstName())) {
                savedVendor.setFirstName(vendor.getFirstName());
                isUpdated = true;
            }
        }

        return isUpdated ? vendorRepository.save(savedVendor) : Mono.just(savedVendor);
    }
}
