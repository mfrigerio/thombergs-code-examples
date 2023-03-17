package io.reflectoring.specification.repository;

import io.reflectoring.specification.model.Distributor;
import io.reflectoring.specification.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductDistributorRepositoryTest {

    @Autowired
    private ProductDistributorRepository productDistributorRepository;

    @Test
    void findByDistributorAddressCity() {
        List<Product> products = productDistributorRepository.findByDistributorAddressCity("chicago");
        products.stream().map(Product::getName).forEach(System.out::println);
        assertEquals(3, products.size());
    }
}
