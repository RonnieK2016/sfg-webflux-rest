package com.udemy.spring5.sfgwebfluxrest.loaders;

import com.udemy.spring5.sfgwebfluxrest.domain.Category;
import com.udemy.spring5.sfgwebfluxrest.domain.Vendor;
import com.udemy.spring5.sfgwebfluxrest.repositories.CategoryRepository;
import com.udemy.spring5.sfgwebfluxrest.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StartupDataLoader implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private VendorRepository vendorRepository;

    public StartupDataLoader(CategoryRepository categoryRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(categoryRepository.count().block() == 0) {
            loadCategories();
        }

        if(vendorRepository.count().block() == 0) {
            loadVendors();
        }
    }

    private void loadVendors() {
        vendorRepository.save(Vendor.builder()
                .firstName("Joe")
                .lastName("Buck").build()).block();

        vendorRepository.save(Vendor.builder()
                .firstName("Micheal")
                .lastName("Weston").build()).block();

        vendorRepository.save(Vendor.builder()
                .firstName("Jessie")
                .lastName("Waters").build()).block();

        vendorRepository.save(Vendor.builder()
                .firstName("Bill")
                .lastName("Nershi").build()).block();

        vendorRepository.save(Vendor.builder()
                .firstName("Jimmy")
                .lastName("Buffett").build()).block();

        System.out.println("Loaded Vendors: " + vendorRepository.count().block());

    }

    private void loadCategories() {
        categoryRepository.save(Category.builder()
                .description("Fruits").build()).block();

        categoryRepository.save(Category.builder()
                .description("Nuts").build()).block();

        categoryRepository.save(Category.builder()
                .description("Breads").build()).block();

        categoryRepository.save(Category.builder()
                .description("Meats").build()).block();

        categoryRepository.save(Category.builder()
                .description("Eggs").build()).block();

        System.out.println("Loaded Categories: " + categoryRepository.count().block());
    }
}
