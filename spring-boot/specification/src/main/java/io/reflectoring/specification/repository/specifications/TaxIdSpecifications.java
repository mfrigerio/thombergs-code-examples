package io.reflectoring.specification.repository.specifications;

import io.reflectoring.specification.model.TaxId;
import io.reflectoring.specification.model.TaxId_;
import io.reflectoring.specification.repository.PathSpecification;

public class TaxIdSpecifications {

    public static PathSpecification<TaxId> vatNumberLike(String vatNumber) {
        return (path, cq, cb) -> cb.like(path.get(TaxId_.vatNumber), "%" + vatNumber + "%");
    }
}
