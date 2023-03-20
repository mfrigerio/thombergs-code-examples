package io.reflectoring.specification.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import javax.persistence.metamodel.SingularAttribute;
import java.util.Collection;

import static io.reflectoring.specification.repository.PathBuilder.fromAttribute;

public interface JpaSpecificationBuilder<T> {

    default <X> Specification<T> eq(PathBuilder<T, ? super X> pathBuilder, X value) {
        return (root, query, builder) -> builder.equal(pathBuilder.toPath(root), value);
    }

    default <X> Specification<T> eq(SingularAttribute<? super T, X> attribute1, X value) {
        return eq(fromAttribute(attribute1), value);
    }

    default Specification<T> like(PathBuilder<T, String> pathBuilder, String substring) {
        return (root, query, builder) -> builder.like(pathBuilder.toPath(root), likePattern(substring));
    }

    default Specification<T> like(SingularAttribute<? super T, String> attribute, String substring) {
        return like(fromAttribute(attribute), substring);
    }

    default String likePattern(String substring) {
        return "%" + substring + "%";
    }

    default Specification<T> startsWith(SingularAttribute<? super T, String> attribute, String prefix) {
        return (root, query, builder) -> builder.like(root.get(attribute), prefix + "%");
    }

    default Specification<T> endsWith(SingularAttribute<? super T, String> attribute, String suffix) {
        return (root, query, builder) -> builder.like(root.get(attribute), "%" + suffix);
    }

    default <X> Specification<T> in(SingularAttribute<? super T, X> attribute, Collection<X> values) {
        return (root, query, builder) -> root.get(attribute).in(values);
    }

    default <X> Specification<T> isNull(SingularAttribute<? super T, X> attribute) {
        return (root, query, builder) -> builder.isNull(root.get(attribute));
    }

    default <X> Specification<T> isNotNull(SingularAttribute<? super T, X> attribute) {
        return (root, query, builder) -> builder.isNotNull(root.get(attribute));
    }

    default Specification<T> isTrue(SingularAttribute<? super T, Boolean> attribute) {
        return (root, query, builder) -> builder.isTrue(root.get(attribute));
    }

    default Specification<T> isFalse(SingularAttribute<? super T, Boolean> attribute) {
        return (root, query, builder) -> builder.isFalse(root.get(attribute));
    }
}
