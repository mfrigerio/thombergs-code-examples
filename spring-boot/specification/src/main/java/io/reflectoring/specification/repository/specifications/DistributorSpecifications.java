package io.reflectoring.specification.repository.specifications;

import io.reflectoring.specification.model.Address;
import io.reflectoring.specification.model.Distributor;
import io.reflectoring.specification.model.Distributor_;
import io.reflectoring.specification.repository.PathSpecification;
import org.springframework.data.jpa.domain.Specification;

public class DistributorSpecifications {

    public static Specification<Distributor> nameLike(String name) {
        return (root, cq, cb) -> cb.like(root.get(Distributor_.name), "%" + name + "%");
    }

    public static Specification<Distributor> address(PathSpecification<Address> addressSpecification) {
        return addressSpecification.atPath(Distributor_.address);
    }
}
