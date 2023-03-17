package io.reflectoring.specification.repository;

import io.reflectoring.specification.model.Address;
import io.reflectoring.specification.model.Address_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.metamodel.SingularAttribute;
import java.util.List;

import static io.reflectoring.specification.repository.specifications.AddressSpecifications.cityLike;
import static org.springframework.data.jpa.domain.Specification.where;

public interface AddressRepository extends
    JpaRepository<Address, String>,
    JpaSpecificationExecutor<Address>,
    JpaSpecificationBuilder<Address> {

    default List<Address> findByCityLike(String city) {
        return findAll(
            where(cityLike(city).atRoot())
        );
    }

}
