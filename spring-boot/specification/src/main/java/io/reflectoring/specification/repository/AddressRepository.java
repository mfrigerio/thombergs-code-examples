package io.reflectoring.specification.repository;

import io.reflectoring.specification.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

import static io.reflectoring.specification.repository.specifications.AddressSpecifications.cityLike;
import static org.springframework.data.jpa.domain.Specification.where;

public interface AddressRepository extends JpaRepository<Address, String>, JpaSpecificationExecutor<Address> {

    default List<Address> findByCityLike(String name) {
        return findAll(
            where(cityLike(name).atRoot())
        );
    }

}
