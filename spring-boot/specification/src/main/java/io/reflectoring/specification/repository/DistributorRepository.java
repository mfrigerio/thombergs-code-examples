package io.reflectoring.specification.repository;

import io.reflectoring.specification.model.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

import static io.reflectoring.specification.repository.PathBuilder.from;
import static io.reflectoring.specification.repository.specifications.AddressSpecifications.cityLike;
import static javax.persistence.criteria.JoinType.LEFT;
import static org.springframework.data.jpa.domain.Specification.where;

public interface DistributorRepository extends
    JpaRepository<Distributor, String>,
    JpaSpecificationExecutor<Distributor>,
    JpaSpecificationBuilder<Distributor> {

    default List<Distributor> findByDistributorName(String name) {
        return findAll(
            where(nameLike(name))
        );
    }

    default List<Distributor> findByDistributorNameAndCity(String name, String city) {
        return findAll(
            where(nameLike(name)
                .and((cityLike(city).atPath(from(Distributor_.address)))))
        );
    }

    default List<Distributor> findByPrimaryAndSecondaryAddressCity(String city) {
        return findAll(
            where(cityLike(city).atPath(Distributor_.address))
                .or(cityLike(city).atPath(Distributor_.secondaryAddresses, LEFT))
        );
    }

    default List<Distributor> findByDistributorVatNumber(String vatNumber) {
        return findAll(
            where(vatNumberLike(vatNumber).atPath(Distributor_.taxId))
        );
    }

    private Specification<Distributor> nameLike(String name) {
        return like(Distributor_.name, name);
    }

    private PathSpecification<TaxId> vatNumberLike(String vatNumber) {
        return (path, cq, cb) -> cb.like(path.get(TaxId_.vatNumber), "%" + vatNumber + "%");
    }
}
