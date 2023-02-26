package io.reflectoring.specification.repository;

import io.reflectoring.specification.model.Distributor;
import io.reflectoring.specification.model.Distributor_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.JoinType;
import java.util.List;

import static io.reflectoring.specification.repository.specifications.AddressSpecifications.cityLike;
import static io.reflectoring.specification.repository.specifications.DistributorSpecifications.*;
import static io.reflectoring.specification.repository.specifications.TaxIdSpecifications.vatNumberLike;
import static org.springframework.data.jpa.domain.Specification.where;

public interface DistributorRepository extends JpaRepository<Distributor, String>, JpaSpecificationExecutor<Distributor> {

    default List<Distributor> findByDistributorName(String name) {
        return findAll(
            where(nameLike(name))
        );
    }

    default List<Distributor> findByDistributorNameAndCity(String name, String city) {
        return findAll(
            where(nameLike(name)
                .and(address(cityLike(city))))
        );
    }

    default List<Distributor> findByPrimaryAndSecondaryAddressCity(String city) {
        return findAll(
            where(cityLike(city).atPath(Distributor_.address))
                .or(cityLike(city).atPath(Distributor_.secondaryAddresses, JoinType.LEFT))
        );
    }

    default List<Distributor> findByDistributorVatNumber(String vatNumber) {
        return findAll(
            where(vatNumberLike(vatNumber).atPath(Distributor_.taxId))
        );
    }
}
