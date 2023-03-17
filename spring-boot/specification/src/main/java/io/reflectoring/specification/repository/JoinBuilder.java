package io.reflectoring.specification.repository;

import javax.persistence.criteria.*;
import javax.persistence.metamodel.*;

import java.util.Set;

import static javax.persistence.criteria.JoinType.INNER;

@FunctionalInterface
public interface JoinBuilder<T, X> extends PathBuilder<T, X> {

    Join<?, X> toPath(Root<T> root);

    static <T, X> JoinBuilder<T, X> fromJoin(JoinBuilder<T, X> pathBuilder) {
        return pathBuilder;
    }

    static <T, X> JoinBuilder<T, X> fromJoin(SingularAttribute<? super T, X> attribute, JoinType joinType) {
        return (root) -> root.join(attribute, joinType);
    }

    static <T, X> JoinBuilder<T, X> fromJoin(SingularAttribute<? super T, X> attribute) {
        return (root) -> root.join(attribute);
    }

    static <T, X> JoinBuilder<T, X> fromJoin(SetAttribute<? super T, X> attribute, JoinType joinType) {
        return (root) -> root.join(attribute, joinType);
    }

    static <T, X> JoinBuilder<T, X> fromJoin(SetAttribute<? super T, X> attribute) {
        return fromJoin(attribute, INNER);
    }

    static <T, X> JoinBuilder<T, X> fromJoin(ListAttribute<? super T, X> attribute, JoinType joinType) {
        return (root) -> root.join(attribute, joinType);
    }

    static <T, X> JoinBuilder<T, X> fromJoin(ListAttribute<? super T, X> attribute) {
        return fromJoin(attribute, INNER);
    }

    static <T, X> JoinBuilder<T, X> fromJoin(CollectionAttribute<? super T, X> attribute, JoinType joinType) {
        return (root) -> root.join(attribute, joinType);
    }

    static <T, X> JoinBuilder<T, X> fromJoin(CollectionAttribute<? super T, X> attribute) {
        return fromJoin(attribute, INNER);
    }

    default <Y> JoinBuilder<T, Y> join(SingularAttribute<? super X, Y> attribute, JoinType joinType) {
        return (root) -> this.toPath(root).join(attribute, joinType);
    }

    default <Y> JoinBuilder<T, Y> join(SingularAttribute<? super X, Y> attribute) {
        return join(attribute,INNER);
    }

    default <Y> JoinBuilder<T, Y> join(SetAttribute<? super X, Y> attribute, JoinType joinType) {
        return (root) -> this.toPath(root).join(attribute, joinType);
    }

    default <Y> JoinBuilder<T, Y> join(SetAttribute<? super X, Y> attribute) {
        return join(attribute, INNER);
    }

    default <Y> JoinBuilder<T, Y> join(ListAttribute<? super X, Y> attribute, JoinType joinType) {
        return (root) -> this.toPath(root).join(attribute);
    }

    default <Y> JoinBuilder<T, Y> join(ListAttribute<? super X, Y> attribute) {
        return join(attribute, INNER);
    }

    default <Y> JoinBuilder<T, Y> join(CollectionAttribute<? super X, Y> attribute, JoinType joinType) {
        return (root) -> this.toPath(root).join(attribute);
    }

    default <Y> JoinBuilder<T, Y> join(CollectionAttribute<? super X, Y> attribute) {
        return join(attribute, INNER);
    }

}
