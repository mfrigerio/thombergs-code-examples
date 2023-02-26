package io.reflectoring.specification.repository;

import io.reflectoring.specification.model.Distributor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DistributorRepositoryTest {

    @Autowired
    private DistributorRepository distributorRepository;

    @Test
    void getDistributorsByNameLike() {
        List<Distributor> distributors = distributorRepository.findByDistributorName("doe");
        assertEquals(1, distributors.size());
    }

    @Test
    void getDistributorsByNameLikeAndCityLike() {
        List<Distributor> distributors = distributorRepository.findByDistributorNameAndCity("parker", "hells");
        assertEquals(1, distributors.size());
    }

    @Test
    void getDistributorsByPrimaryAndSecondaryAddressCityLike() {
        List<Distributor> distributors = distributorRepository.findByPrimaryAndSecondaryAddressCity("chicago");
        distributors.stream().map(Distributor::getName).forEach(System.out::println);
        assertEquals(2, distributors.size());
    }

    @Test
    void getDistributorsByTaxIdLike() {
        List<Distributor> distributors1 = distributorRepository.findByDistributorVatNumber("123");
        assertEquals(1, distributors1.size());

        List<Distributor> distributors2 = distributorRepository.findByDistributorVatNumber("3456");
        assertEquals(3, distributors2.size());
    }

}
