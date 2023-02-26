package io.reflectoring.specification.repository.specifications;

import io.reflectoring.specification.model.Address;
import io.reflectoring.specification.model.Address_;
import io.reflectoring.specification.repository.PathSpecification;

public class AddressSpecifications {

    public static PathSpecification<Address> cityLike(String city) {
        return (path, cq, cb) ->
            cb.like(path.get(Address_.city), "%" + city + "%");
    }

}
