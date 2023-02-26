package io.reflectoring.specification.repository;

import io.reflectoring.specification.model.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Test
    void getDistributorsByNameLike() {
        List<Address> addresses = addressRepository.findByCityLike("chicago");
        assertEquals(2, addresses.size());
    }

}
