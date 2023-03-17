package io.reflectoring.specification.repository;

import javax.persistence.criteria.*;
import javax.persistence.metamodel.*;

import java.util.Set;

import static javax.persistence.criteria.JoinType.INNER;
import static javax.persistence.metamodel.Attribute.PersistentAttributeType.BASIC;

@FunctionalInterface
public interface PathBuilder<T, X> {

    Path<X> toPath(Root<T> root);

    static <T, X> PathBuilder<T, X> fromRoot(PathBuilder<T, X> pathBuilder) {
        return pathBuilder;
    }

    static <T, X> PathBuilder<T, X> fromAttribute(SingularAttribute<? super T, X> attribute) {
        return (root) -> root.get(attribute);
    }

    default <Y> PathBuilder<T, Y> get(SingularAttribute<? super X, Y> attribute) {
        return (root) -> this.toPath(root).get(attribute);
    }

}
