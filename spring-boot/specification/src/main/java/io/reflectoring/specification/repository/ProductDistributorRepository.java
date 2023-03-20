package io.reflectoring.specification.repository;

import io.reflectoring.specification.model.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

import static io.reflectoring.specification.repository.JoinBuilder.fromJoin;
import static io.reflectoring.specification.repository.PathBuilder.fromAttribute;
import static io.reflectoring.specification.repository.PathBuilder.fromRoot;
import static javax.persistence.criteria.JoinType.LEFT;
import static org.springframework.data.jpa.domain.Specification.where;

public interface ProductDistributorRepository extends
    JpaRepository<Product, String>,
    JpaSpecificationExecutor<Product>,
    JpaSpecificationBuilder<Product> {

    default List<Product> findByDistributorAddressCity(String city) {
        return findAll(where(
            distributorCityLike(city)
        ));
    }

    private Specification<Product> distributorCityLike(String city) {
        return like(fromAttribute(Product_.distributor)
                .get(Distributor_.address)
                .get(Address_.city)
            , city)
            .or(
                like(fromJoin(Product_.distributor)
                        .join(Distributor_.secondaryAddresses)
                        .get(Address_.city)
                    , city)
            ).or(
                like(
                    fromRoot((root) -> root.join(Product_.distributor)
                        .join(Distributor_.secondaryAddresses)
                        .get(Address_.city)
                    ), city)
            ).or(
                addressCityLike(city).atPath(
                    fromRoot((root) -> root.join(Product_.distributor)
                        .join(Distributor_.secondaryAddresses)
                    )
                )
            ).or(
                addressCityLike(city).atPath(
                    fromJoin(Product_.distributor)
                        .join(Distributor_.secondaryAddresses, LEFT)
                )
            ).or(
                distributorAddress(addressCityLike(city))
            );
    }

    private Specification<Product> distributorAddress(PathSpecification<Address> pathSpecification) {
        return pathSpecification.atPath(fromJoin(Product_.distributor).join(Distributor_.address));
    }

    private PathSpecification<Address> addressCityLike(String city) {
        return (path, query, builder) -> builder.like(path.get(Address_.city), "%" + city + "%");
    }
}
