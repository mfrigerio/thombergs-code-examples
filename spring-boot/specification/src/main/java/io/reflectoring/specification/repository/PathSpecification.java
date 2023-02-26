package io.reflectoring.specification.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import javax.persistence.criteria.*;
import javax.persistence.metamodel.*;

@FunctionalInterface
public interface PathSpecification<T> {

    Predicate toPredicate(Path<T> path, CriteriaQuery<?> query, CriteriaBuilder builder);

    default Specification<T> atRoot() {
        return this::toPredicate;
    }

    default <S> Specification<S> atPath(final SingularAttribute<S, T> attribute, JoinType joinType) {
        return (root, query, builder) -> toPredicate(root.join(attribute, joinType), query, builder);
    }

    default <S> Specification<S> atPath(final SingularAttribute<S, T> attribute) {
        return atPath(attribute, JoinType.INNER);
    }

    default <S> Specification<S> atPath(final SetAttribute<S, T> attribute, JoinType joinType) {
        return (root, query, builder) -> toPredicate(root.join(attribute, joinType), query, builder);
    }

    default <S> Specification<S> atPath(final SetAttribute<S, T> attribute) {
        return atPath(attribute, JoinType.INNER);
    }

    default <S> Specification<S> atPath(final ListAttribute<S, T> attribute, JoinType joinType) {
        return (root, query, builder) -> toPredicate(root.join(attribute, joinType), query, builder);
    }

    default <S> Specification<S> atPath(final ListAttribute<S, T> attribute) {
        return atPath(attribute, JoinType.INNER);
    }

    default <S> Specification<S> atPath(final CollectionAttribute<S, T> attribute, JoinType joinType) {
        return (root, query, builder) -> toPredicate(root.join(attribute), query, builder);
    }

    default <S> Specification<S> atPath(final CollectionAttribute<S, T> attribute) {
        return atPath(attribute, JoinType.INNER);
    }
}
