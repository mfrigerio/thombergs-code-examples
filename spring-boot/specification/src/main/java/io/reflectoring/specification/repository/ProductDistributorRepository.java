package io.reflectoring.specification.repository;

import io.reflectoring.specification.model.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

import static io.reflectoring.specification.repository.JoinBuilder.fromJoin;
import static io.reflectoring.specification.repository.PathBuilder.fromRoot;
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
        return like(fromJoin(Product_.distributor)
                .get(Distributor_.address)
                .get(Address_.city)
            , city)
            .or(
                like(fromRoot((root) ->
                    root.join(Product_.distributor)
                        .get(Distributor_.address)
                        .get(Address_.city)
                ), city)
            );
//        return like(
//            from((root) -> root.join(Product_.distributor)
//                .join(Distributor_.secondaryAddresses)
//                .get(Address_.city)
//            ), city);
//        return addressCityLike(city).atPath(
//            from((root) -> root.join(Product_.distributor)
//                .join(Distributor_.secondaryAddresses)
//            )
//        );
//        return addressCityLike(city).atPath(
//            PathBuilder.from(Product_.distributor)
//                .join(Distributor_.secondaryAddresses)
//        );
//        return like(
//            from(Product_.distributor)
//                .get(Distributor_.secondaryAddresses)
//                .get(Address_.city)
//            , city);
//        return addressCityLike(city).atPath(
//            from(Product_.distributor).join(Distributor_.secondaryAddresses, JoinType.LEFT)
//        );
//        return distributorAddress(addressCityLike(city));
    }

    private Specification<Product> distributorAddress(PathSpecification<Address> pathSpecification) {
        return pathSpecification.atPath(fromJoin(Product_.distributor).join(Distributor_.address));
    }

    private PathSpecification<Address> addressCityLike(String city) {
        return (path, query, builder) -> builder.like(path.get(Address_.city), "%" + city + "%");
    }
}
